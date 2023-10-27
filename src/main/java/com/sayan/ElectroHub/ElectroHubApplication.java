package com.sayan.ElectroHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElectroHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectroHubApplication.class, args);
	}

}
