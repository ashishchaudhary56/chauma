package com.ipm.api;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class InsurencePolicyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurencePolicyManagementApplication.class, args);
		
	}

}
