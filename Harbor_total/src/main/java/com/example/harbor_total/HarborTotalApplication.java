package com.example.harbor_total;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class HarborTotalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborTotalApplication.class, args);
	}

}
