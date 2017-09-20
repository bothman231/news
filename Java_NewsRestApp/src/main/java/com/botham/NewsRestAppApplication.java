package com.botham;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// All classes with the same root package as this one..
// com.botham will be scanned for @Component etc
@SpringBootApplication
public class NewsRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsRestAppApplication.class, args);
	}
}
