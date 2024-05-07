package com.example.harbor_batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableBatchProcessing
@EnableDiscoveryClient
public class HarborBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarborBatchApplication.class, args);
    }

}
