package com.botham.rest.jobs;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.botham.base.constants.GlobalConstants;
import com.botham.news.db.DbRepository;
import com.botham.news.db.customer.CustomerRepository;
import com.botham.news.db.jobs.JobsRepository;
import com.botham.news.domain.Db;
import com.botham.news.domain.customer.Customer;
import com.botham.news.domain.jobs.Jobs;



@SuppressWarnings("deprecation")
@RestController
@ContextConfiguration(classes = { JobsConfig.class, CustomerConfig.class })

public class NewsRestJobsController {

// http://localhost:8089/jobs
	
   Logger log = LoggerFactory.getLogger(NewsRestJobsController.class);
	
   @Autowired
   JobsRepository jobsRepository;
   
   
   @Autowired
   CustomerRepository customerRepository;
   
   @Autowired
   DbRepository dbRepository;
   
   @Consumes({"application/xml", "application/json","text/plain,text/html"})
   @Produces({"application/xml", "application/json","text/plain,text/html"})
   @RequestMapping("/jobs")
   @ResponseBody
   public ResponseEntity<JobsResult> getMultiple(@RequestParam(value="name", defaultValue="World") String name) {
	   String mName="getMultiple";
	   
	   JobsResult jr = new JobsResult();
	   
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
       getCustomers();

       
       getDb();
       
       getPostDb();
	   
	   jr.setOutput("OUTPUT");
	   ResponseEntity<JobsResult> re = new ResponseEntity<>(jr, HttpStatus.OK);
	   if (re.hasBody()) {
		   //
	   }
	   return re;
   }
   
   

   
   @Transactional("customerTransactionManager")
   public void getCustomers() {
	   String mName="getCustomers";
	   if (log.isDebugEnabled()) {
		   log.debug(mName+GlobalConstants.STARTS);
	   }
	   
       List<Customer> clist = customerRepository.findAll();
       
       for (Customer c : clist) {
	      if (log.isDebugEnabled()) {
	    	  log.debug(mName+" "+c.getId());
	      }
       }

   }
   
   
   @Transactional("customerTransactionManager")
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
   public List<Jobs> getJobs() {
	   String mName="getJobs";
	   if (log.isDebugEnabled()) {
		   log.debug(mName+GlobalConstants.STARTS);
	   }

	   return jobsRepository.findAll(); 	

   }
   
   
   @Transactional("jobsTransactionManager")
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
   
   @Transactional("customerTransactionManager")
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
