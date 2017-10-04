package com.botham.news.rest.Java_NewsRestApp1;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
	@SpringBootApplication
	public class MyRestController {
	
	
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
	   
	}

