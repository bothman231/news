package com.botham.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.botham.db.resource.ResourceRepository;
import com.botham.domain.resource.Info;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Configuration
@SpringBootApplication
public class MyRestController {
	
	   @Autowired
	   ResourceRepository repository;
	   
    private static final Logger log = LoggerFactory.getLogger(MyRestController.class);
    
    
	   @Consumes({"*", "application/xml", "application/json", "text/plain,text/html"})
	   @Produces({"application/xml", "application/json", "text/plain,text/html"})
	   @RequestMapping("/api/checkin")
	   @ResponseBody
	   public ResponseEntity<String> processCheckIn(RequestEntity<Info> request) 
			   throws 
			                         JsonParseException, JsonMappingException, IOException {
		   
		   String mName="processCheckin -------------------";
		   if (log.isInfoEnabled()) {
			   log.info(mName+" Starts, request="+request.getBody());
		   }
		   
		   
		   // Update the DB here ...
		   
		   repository.findAll();
		   
		   
		   
		
		   ObjectMapper mapper = new ObjectMapper();
		   Info info = mapper.readValue(request.getBody().toString(), Info.class);
		   processCheckin(info);

		   
		   
		   
		   
		   
		   if (log.isInfoEnabled()) {
			   log.info(mName+" Ends");	
		   }
		   return new ResponseEntity(HttpStatus.OK);
	   }
	   
	   
	   public void processCheckin(Info info) {
		   
		   String mName="processCheckin(Info)";
		   /*
		   if (log.isInfoEnabled()) {
			   log.info(mName+" Starts, info="+info.toString());
		   }
		   */
		   
		   Date now = new Date(System.currentTimeMillis());
		   log.debug(mName+" "+info.getSystemName()+" checking in at "+now);
		   
	   }

}
