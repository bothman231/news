package com.botham.rest.jobs;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.botham.base.GlobalConstants;
import com.botham.news.db.DbRepository;
import com.botham.news.db.customer.CustomerRepository;
import com.botham.news.db.jobs.JobsRepository;
import com.botham.news.domain.Db;
import com.botham.news.domain.customer.Customer;
import com.botham.news.domain.jobs.Jobs;

@RestController
@ContextConfiguration(classes = { JobsConfig.class, CustomerConfig.class })
@SpringBootApplication
public class NewsRestJobsController {

// http://localhost:8089/jobs
	
   Logger log = LoggerFactory.getLogger(NewsRestJobsController.class);
	
   @Autowired
   JobsRepository jobsRepository;
   
   
   @Autowired
   CustomerRepository customerRepository;
   
   @Autowired
   DbRepository dbRepository;
   
   @Consumes({"application/xml", "application/json", "text/plain,text/html"})
   @Produces({"application/xml", "application/json", "text/plain,text/html"})
   @RequestMapping("/jobs")
   @ResponseBody
   public ResponseEntity<JobsResult> getMultiple(@RequestParam(value="name", defaultValue="World") String name) {
	   String mName="getMultiple";
	   
	   JobsResult jr = new JobsResult();
	   boolean quickTest=true;
	   if (quickTest) {
		   jr.setOutput("OUTPUT");
		   ResponseEntity<JobsResult> re = new ResponseEntity<>(jr, HttpStatus.OK);
		   if (re.hasBody()) {
			   //
		   }
		   return re;
	   }
	   
	   int x=0;

	   List<Jobs> list=getJobs();
	   
	   for (Jobs j : list) {
		   x++;
		   if (log.isDebugEnabled()) {
			   log.debug(mName+" x="+x+" jobs="+j.getDescription());
		   }
		   jr.getList().add(j);
	   }
	  
	   insertCustomers();
	   
       CustomerHelper.getCustomers();

       
       getDb();
       
       getPostDb();
	   
	   jr.setOutput("OUTPUT");
	   ResponseEntity<JobsResult> re = new ResponseEntity<>(jr, HttpStatus.OK);
	   if (re.hasBody()) {
		   //
	   }
	   return re;
   }
   
   

   @Consumes({"*", "application/xml", "application/json", "text/plain,text/html"})
   @Produces({"application/xml", "application/json", "text/plain,text/html"})
   @RequestMapping("/j")
   @ResponseBody
   public ResponseEntity<JobsResult> get() {
	   String mName="get";
	   if (log.isDebugEnabled()) {
		   log.debug(mName+" Starts");
	   }
	   
	   JobsResult jr = new JobsResult();
	   boolean quickTest=true;
	   if (quickTest) {
		   jr.setOutput("OUTPUT");
		   ResponseEntity<JobsResult> re = new ResponseEntity<>(jr, HttpStatus.OK);
		   if (re.hasBody()) {
			   //
		   }
		   return re;
	   }
	   return null;
   }

   
   
   @Transactional("customerTransactionManager")
   //@AliasFor(value="customerTransactionManager")
   public void insertCustomers() {
	   String mName="insertCustomers";
	   if (log.isDebugEnabled()) {
		   log.debug(mName+GlobalConstants.STARTS);
	   }
	   
	   
	   Customer customer = new Customer("100","Manchester United");
	   
	   Customer inserted = customerRepository.save(customer);
	   if (inserted==null) {
		   if (log.isErrorEnabled()) {
			   log.error(mName+" insert failed");
		   }
	   } else {
		   if (log.isErrorEnabled()) {
		      log.error(mName+" customer="+inserted);
		   }
	   }
	   
	  

   }
   
   
   @Transactional("jobsTransactionManager")
   //@AliasFor(value="jobsTransactionManager")
   public List<Jobs> getJobs() {
	   String mName="getJobs";
	   if (log.isDebugEnabled()) {
		   log.debug(mName+GlobalConstants.STARTS);
	   }

	   return jobsRepository.findAll(); 	

   }
   
   @Transactional("jobsTransactionManager")
   //@AliasFor(value="jobsTransactionManager")
   public void getDb() {
	   String mName="getDb";
	   if (log.isDebugEnabled()) {
		   log.debug(mName+GlobalConstants.STARTS);
	   }
	   
       List<Db> clist = dbRepository.findAll();
       
       for (Db c : clist) {
	      if (log.isDebugEnabled()) {
	    	  log.debug(mName+" "+c.getKey()+" "+" "+c.getDesc());
	      }
       }

   }
   
   @Transactional("jobsTransactionManager")
   //@AliasFor(value="jobsTransactionManager")
   public void getPostDb() {
	   String mName="getPostDb";
	   if (log.isDebugEnabled()) {
		   log.debug(mName+GlobalConstants.STARTS);
	   }
	   
       List<Db> clist = dbRepository.findAll();
       
       for (Db c : clist) {
    	   if (log.isDebugEnabled()) {
    		   log.debug(mName+" "+c.getKey()+" "+" "+c.getDesc());    		   
    	   }
       }

   }
   
   @Transactional("customerTransactionManager")
   //@AliasFor(value="customerTransactionManager")
   public void insertDb() {
	   String mName="insertDb";
	   if (log.isDebugEnabled()) {
		   log.debug(mName+GlobalConstants.STARTS);
	   }
	   
	   
	   Db db = new Db("key","POSTGRESQL");
	   
	   Db inserted = dbRepository.save(db);
	   if (inserted==null) {
		   if (log.isErrorEnabled()) {
			   log.error(mName+" insert failed");
		   }
	   } else {
		   if (log.isErrorEnabled()) {
			   log.error(mName+" entity="+inserted);
		   }
	   }
	   
	  

   }

   
}
