package com.example.harbor_salary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class
HarborSalaryApplication {
    public static void main(String[] args) {
        SpringApplication.run(HarborSalaryApplication.class, args);
    }
}
