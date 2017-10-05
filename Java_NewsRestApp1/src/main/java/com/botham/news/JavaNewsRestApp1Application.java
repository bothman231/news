package com.botham.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {"com.botham"})
public class JavaNewsRestApp1Application {

	public static void main(String[] args) {
		SpringApplication.run(JavaNewsRestApp1Application.class, args);
	}
}
