package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import static org.springframework.context.annotation.FilterType.CUSTOM;

@SpringBootApplication
//@ EnableGlobalMethodSecurity(securedEnabled = true)
public class GamerListApplication {

    @Bean
    public String message() {
        return "Game not found";
    }

    public static void main(String[] args) {
        SpringApplication.run(GamerListApplication.class, args);
    }

}
