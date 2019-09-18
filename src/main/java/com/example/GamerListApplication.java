package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class GamerListApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamerListApplication.class, args);
	}

}
