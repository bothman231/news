package com.botham.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.botham.base.BaseHelper;
import com.botham.base.Info;
import com.botham.db.resource.ResourceRepository;
import com.botham.domain.resource.Resource;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class MyRestController {
	
	   @Autowired
	   ResourceRepository repository;
	      
	    @Value("${spring.datasource.url}")
	    public String springDatasourceUrl;
	    
		private final String cName="Server ";
		
    private static final Logger log = LoggerFactory.getLogger(MyRestController.class);
    
    
	   @Consumes({"application/xml", "application/json", "text/plain,text/html"})
	   @Produces({"application/xml", "application/json", "text/plain,text/html"})
	   @RequestMapping("/api/checkin")
	   @ResponseBody
	   public ResponseEntity<String> processCheckIn(RequestEntity<Info> request) 
			   throws 
			                         JsonParseException, JsonMappingException, IOException {
		   
		   String mName="processCheckin -------------------";
		   if (log.isInfoEnabled()) {
			   log.info(cName+mName+" Starts, request="+request.getBody());
		   }
		   
		   
		   String build=BaseHelper.getBuildInfo();
		   
		   
		   log.info(mName+" ");
		   log.info(mName+" springDatasourceUrl="+springDatasourceUrl);
		   
		   processCheckin(request.getBody());
		   
		   log.info(mName+" ");
		   log.info(mName+" ");
		   
		   if (log.isInfoEnabled()) {
			   log.info(mName+" Ends");	
		   }
		   
		   return new ResponseEntity(HttpStatus.OK);
	   }
	   
	   
	   @Transactional
	   public void processCheckin(Info info) {
		   
		   String mName="processCheckin(Info)";
		   
		   if (log.isInfoEnabled()) {
			   log.info(mName+" Starts, info="+info.toString());
		   }
		   
	       String thisSystemName=System.getenv("B_SYSTEM_NAME");
	       log.debug(mName+" B_SYSTEM_NAME="+thisSystemName);
		   		   
		   Date nowSqlDate = new Date(System.currentTimeMillis());
		   Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		   java.util.Date nowDate = new java.util.Date(System.currentTimeMillis());

		   log.info(mName+" "+info.getSystemName()+" checking in at "+nowSqlDate+" "+nowDate);
		   		   
		   Resource exists = new Resource();
		   exists.setId(info.getSystemName());
		   exists = repository.findOne(info.getSystemName());
		   if (exists!=null) {
			   exists.setLastCheckin(timestamp);
			   exists.setCheckinCount(exists.getCheckinCount().add(new BigDecimal("1.00")));
			   exists.setInfo(info.getJavaVersion()+" "+info.getBuild());
			   exists.setCheckinServer(thisSystemName);
			   exists.setTimeAtRemote(info.getLocalTime());
			   repository.save(exists);
		   } else {
			   Resource new1 = new Resource();
			   new1.setId(info.getSystemName());
			   new1.setLastCheckin(timestamp);
			   new1.setCheckinCount(new BigDecimal("1.00"));
			   new1.setInfo(info.getJavaVersion()+" "+info.getBuild());
			   new1.setCheckinServer(thisSystemName);
			   new1.setTimeAtRemote(info.getLocalTime());
			   repository.save(new1);
		   }
		   
	   }
	   
	   
	   @PostConstruct
	   public void postConstruct() {
		   String mName="postContruct";
		   if (log.isDebugEnabled()) {
			   log.debug(mName+" Controller is up");
		   }
	   }

}
