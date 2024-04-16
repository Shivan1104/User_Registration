package com.user; 
// Package declaration indicating the location of the class

import org.springframework.boot.SpringApplication;  
// Importing SpringApplication class from Spring Boot

import org.springframework.boot.autoconfigure.SpringBootApplication; 
// Importing SpringBootApplication annotation from Spring Boot


@SpringBootApplication 
// Annotation to denote that this class is a Spring Boot application and enables auto-configuration

public class UserRegistrationApplication {  
	// Declaration of the UserRegistrationApplication class

    public static void main(String[] args) {  
    	// Main method
    	
        SpringApplication.run(UserRegistrationApplication.class, args);
        // Running the Spring Boot application
    }

}

