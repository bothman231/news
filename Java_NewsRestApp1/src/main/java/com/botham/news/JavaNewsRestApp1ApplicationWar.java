package com.botham.news;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.botham"})
public class JavaNewsRestApp1ApplicationWar extends SpringBootServletInitializer {

	    @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(JavaNewsRestApp1ApplicationWar.class);
	    }
}
