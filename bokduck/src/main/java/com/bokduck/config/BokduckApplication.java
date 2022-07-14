package com.bokduck.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bokduck")
public class BokduckApplication {

	public static void main(String[] args) {
		SpringApplication.run(BokduckApplication.class, args);
	}

}
