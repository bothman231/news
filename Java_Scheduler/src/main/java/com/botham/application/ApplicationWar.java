package com.botham.application;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


/*
@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.botham.*"})
public class ApplicationWar extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(ApplicationWar.class);
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    	
    	String mName="ApplicationWar";
    	if (log.isDebugEnabled()) {
    		log.debug(mName+" Starts");
    	}
    	
        return application.sources(ApplicationWar.class);
    }
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationWar.class, args);
    }
    
    

}*/