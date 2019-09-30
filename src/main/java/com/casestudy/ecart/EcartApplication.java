package com.casestudy.ecart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EcartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcartApplication.class, args);
	}

}
