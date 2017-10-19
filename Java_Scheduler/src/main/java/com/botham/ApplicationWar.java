package com.botham;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.botham"})
public class ApplicationWar extends SpringBootServletInitializer {

    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationWar.class);
    }
    
   // public static void main(String[] args) throws Exception {
   //     SpringApplication.run(ApplicationWar.class, args);
   // }

}