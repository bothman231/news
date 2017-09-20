package com.botham.rest.jobs;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.botham.news.db.JobsRepository;
import com.botham.news.domain.Jobs;

@RestController
public class NewsRestJobsController {

// http://localhost:8089/jobs
	
   Logger log = LoggerFactory.getLogger(NewsRestJobsController.class);
	
   @Autowired
   JobsRepository jobsRepository;
   
   @RequestMapping("/jobs")
   @ResponseBody
   public ResponseEntity<JobsResult> getMultiple(@RequestParam(value="name", defaultValue="World") String name) {
	   String mName="getMultiple";
	   
	   System.out.println("hi");
	   JobsResult jr = new JobsResult();
	   

	   int x=0;
	   List<Jobs> list = jobsRepository.findAll();
	   for (Jobs j : list) {
		   x++;
		   log.debug(mName+" x="+x+" jobs="+j.getDescription());
		   jr.getList().add(j);
	   }
	   

	   
	   
	   jr.setOutput("OUTPUT");
	   ResponseEntity<JobsResult> re = new ResponseEntity<JobsResult>(jr, HttpStatus.OK);
	   return re;
   }
   
   
  
}
