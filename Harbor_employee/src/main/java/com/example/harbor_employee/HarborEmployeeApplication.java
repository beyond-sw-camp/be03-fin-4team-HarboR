package com.example.harbor_employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HarborEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborEmployeeApplication.class, args);
	}

}
