package com.example.harbor_salary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HarborSalaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarborSalaryApplication.class, args);
    }

}
