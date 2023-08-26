package com.tech_tribe.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth_service", r -> r.path("/api/auth-service/**")
                        .uri("lb://auth-service"))
                .route("financial_service", r -> r.path("/api/v1/financial-service/**")
                        .uri("lb://financial-service"))
                .route("goal-service", r -> r.path("/api/v1/goal-service/**")
                        .uri("lb://goal-service"))
                .route("notification-service", r -> r.path("/api/v1/notification-service/**")
                        .uri("lb://notification-service"))
                .route("plan-generator-service", r -> r.path("/api/v1/plan-generator-service/**")
                        .uri("lb://plan-generator-service"))
                .build();
    }
}
