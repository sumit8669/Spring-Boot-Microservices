package com.sumit.ms.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApp {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApp.class, args);
	}

}
