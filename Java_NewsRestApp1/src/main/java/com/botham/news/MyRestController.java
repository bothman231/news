package com.botham.news;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.botham.news.db.jobs.JobsRepository;
import com.botham.news.domain.jobs.Jobs;
import com.botham.news.rest.Person;
import com.botham.news.rest.RestEntity;
import com.botham.news.rest.Thing;




//@ComponentScan(basePackages = {"com.botham"})
@RestController
@Configuration
	@SpringBootApplication
	public class MyRestController {
	
	
	   @Autowired
	   JobsRepository jobsRepository;
	   
	   
	
	   Logger log = LoggerFactory.getLogger(MyRestController.class);
	   
	   
	
	   @Consumes({"*", "application/xml", "application/json", "text/plain,text/html"})
	   @Produces({"application/xml", "application/json", "text/plain,text/html"})
	   @RequestMapping("/p")
	   @ResponseBody
	   //public ResponseEntity<String> get() {
	   public ResponseEntity<RestEntity> getPerson() {

		   
		   String desc="A request to http://localhost:8095/j returns a response entity with a preset HttpStatus.OK (200) and a string value in the body";
		   
		   /*

		   
		   ObjectMapper mapper = new ObjectMapper();
		   StringWriter stringEmp = new StringWriter();
		   try {
			mapper.writeValue(stringEmp, personList);
		} catch (JsonGenerationException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		*/

		   
		//ResponseEntity<String> responseEntity = new ResponseEntity<>(desc+""+stringEmp.toString(), HttpStatus.OK);
		
		RestEntity re = new RestEntity();
		re.setObjects((List<Thing>) getThings());
		
		re.setMessage("All Done");
		re.setType("Person");
		
		re.setObjects((List<Person>) getPersons());
		
		ResponseEntity<RestEntity> responseEntity1 = new ResponseEntity<RestEntity>(re, HttpStatus.OK);

		
		   
		return responseEntity1;
	   }
	   
	   
	   
	   
	   @Consumes({"*", "application/xml", "application/json", "text/plain,text/html"})
	   @Produces({"application/xml", "application/json", "text/plain,text/html"})
	   @RequestMapping("/t")
	   @ResponseBody
	   //public ResponseEntity<String> get() {
	   public ResponseEntity<RestEntity> getThing() {

		   
		   String desc="A request to http://localhost:8095/j returns a response entity with a preset HttpStatus.OK (200) and a string value in the body";

		RestEntity re = new RestEntity();
		
		re.setMessage("All Done");
		re.setType("Thing");
		
		re.setObjects((List<Thing>) getThings());
		ResponseEntity<RestEntity> responseEntity1 = new ResponseEntity<RestEntity>(re, HttpStatus.OK);

		   
		return responseEntity1;
	   }
	   
	   
	   public List<Person> getPersons() {
		   Person entity1 = new Person("Steve");
		   Person entity2 = new Person("Stef");
		   List<Person> entityList = new ArrayList<Person>();
		   entityList.add(entity1);
		   entityList.add(entity2);
		   return entityList;
	   }
	   
	   public List<Thing> getThings() {
		   Thing entity1 = new Thing("VW Jetta");
		   Thing entity2 = new Thing("Laptop Computer");
		   List<Thing> entityList = new ArrayList<Thing>();
		   entityList.add(entity1);
		   entityList.add(entity2);
		   return entityList;
	   }
	   
	   
/*	   
	   
	   GET		/jobs	- Give you a list of all jobs, showing id / name and status
	   GET		/jobs/?{id}=1 - Give you a summary of 12 job showing all fields
	   			/jobs/?id=1
	   			 
	   PUT		/jobs/?{id}=1&{status}=A or I - Update the status field on the jobs table.
	   			/jobs/?id=1&status=A
	   			 
	   
	    */

	   
	@Consumes({ "*", "application/xml", "application/json", "text/plain,text/html" })
	@Produces({ "application/xml", "application/json", "text/plain,text/html" })
	@RequestMapping("/jobs")
	@ResponseBody
	public ResponseEntity<RestEntity> jobs(@RequestParam(value="id", defaultValue="0") int id,
			                               @RequestParam(value="status", defaultValue="") String status)
	                                  {
		String mName="jobs";
		if (log.isDebugEnabled()) {
			log.debug(mName+" "+"Starts id="+id+"* status="+status);

		}
		
		RestEntity re = new RestEntity();
		
		if (id==0) {
			if (log.isDebugEnabled()) {
				log.debug(mName+" no id is passed");
			}
			
			List<Jobs> jobsList = jobsRepository.findAll();
			for (Jobs j:jobsList) {
				log.debug(mName+" job="+j.toString());
			}
			re.setObjects((List<Jobs>) jobsList);
			
		} else {
			if (log.isDebugEnabled()) {
				log.debug(mName+" id="+id);
			}
			
			Jobs jobs=jobsRepository.findOne(id);
			
			if (log.isDebugEnabled()) {
			   log.debug(mName+" Jobs="+jobs.toString());
			}
		}
		
		if (status.isEmpty()) {
			if (log.isDebugEnabled()) {
				log.debug(mName+" no status is passed");
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug(mName+" status="+status);
			}
		}
		
		
		//getLocalIp();
		
		String desc = "";



		re.setMessage("All Done");
		re.setType("Jobs");

		//re.setObjects((List<Thing>) getThings());
		ResponseEntity<RestEntity> responseEntity1 = new ResponseEntity<RestEntity>(re, HttpStatus.OK);

		return responseEntity1;
	}
	
	
	public void getLocalIp() {
		InetAddress addr=null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ip = addr.getHostAddress();
		System.out.println("Ip: " + ip);
	}
	   
	}

