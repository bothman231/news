package com.botham;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;



//@SpringBootApplication
//@EnableScheduling
public class ApplicationWar extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(ApplicationWar.class);
    
    
    public static void main(String[] args) throws Exception {
    	String mName="ApplicationWar";
    	System.out.println(mName);
        SpringApplication.run(ApplicationWar.class, args);
    }
    
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	
    	String mName="ApplicationWar";
    	System.out.println(mName);
    	if (log.isDebugEnabled()) {
    		log.debug(mName+" Starts");
    	}
    	
        return application.sources(ApplicationWar.class);
    }
    


}