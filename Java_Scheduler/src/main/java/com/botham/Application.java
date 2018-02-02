package com.botham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.botham.db.resource.ResourceRepository;


// I changed this, and again12

@SpringBootApplication
@EnableScheduling
//@Configuration
// Works for 1,,, but fails when using 2 or more???
//@ComponentScan(basePackages = {"com.botham.db.resource", "com.botham.schedule", 
		//                       "com.botham.controllers", "com.botham"})
//@ComponentScan(basePackages = {"com.botham.db.resource", "com.botham.schedule"})
//
//@ComponentScan(basePackages = {"com.botham.db.resource"})
//@ComponentScan(basePackages = {"com.botham"})
//@ComponentScan({"com.botham.db.resource"})
//, "com.botham.schedule", 
  //      "com.botham.controllers", "com.botham"})
public class Application {

	   @Autowired
	   ResourceRepository repository;
	
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    
    public static void main(String[] args) throws Exception {
    	String mName="Application";
    	if (log.isDebugEnabled()) {
    		log.debug(mName+" Starts");
    	}
    	
    	
    	try {
    	//showContext();
    	
    	
    	SpringApplication app = new SpringApplication(Application.class);
    	
    	//Banner b = new BannerImpl();
    	//app.setBanner("STEVE");
    	
    	//app.getMainApplicationClass();
    	
    	app.run();
    	
    	//SpringApplication.run(Application.class); 
    	} catch (Exception e) {
    		log.error(mName+", Exception on start e.getMessage="+e.getMessage());
    		log.error(mName+", e.getCause="+e.getCause().getMessage());
    		
    	}
    }
    
    
    public static void showContext() {
    	
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        
        try {
            ctx.register(Application.class);
            ctx.refresh();
            //System.out.println("ApplicationExample: " +
            //ctx.getBean("Application"));
            //System.out.println("BeanA: " + ctx.getBean("ResourceRepository"));
         
        } finally {
            ctx.close();
        }
    }
}
