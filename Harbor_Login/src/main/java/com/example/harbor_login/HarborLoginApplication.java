package com.example.harbor_login;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class HarborLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarborLoginApplication.class, args);
	}

//	@Bean
//	public Logger.Level feignLoggerLevel(){
//		return Logger.Level.FULL;
//	}
}
