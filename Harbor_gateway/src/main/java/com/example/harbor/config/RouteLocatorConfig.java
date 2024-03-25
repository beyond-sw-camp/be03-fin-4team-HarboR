package com.example.harbor.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteLocatorConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/login/**")
                        .uri("lb://login"))
                .route(r -> r.path("/employee/**")
                        .uri("lb://employee"))
                .route(r -> r.path("/salary/**")
                        .uri("lb://salary"))
                .route(r -> r.path("/total/**")
                        .uri("lb://total"))
                .build();
    }
}
