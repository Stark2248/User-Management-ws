package com.silverlining.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersManagementWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersManagementWsApplication.class, args);
	}

}
