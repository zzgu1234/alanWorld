package com.bokduck.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan(basePackages = "com.bokduck")
@EnableJpaRepositories(basePackages = "com.bokduck.api")
@EntityScan(basePackages = "com.bokduck.api")
public class BokduckApplication {

	public static void main(String[] args) {
		SpringApplication.run(BokduckApplication.class, args);
	}

}
