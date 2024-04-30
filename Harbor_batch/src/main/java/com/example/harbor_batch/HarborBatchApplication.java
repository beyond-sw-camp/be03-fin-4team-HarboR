package com.example.harbor_batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class HarborBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarborBatchApplication.class, args);
    }

}
