package com.amit.gatewayserver;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	
	@Bean
	RouteLocator amitBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		
		return routeLocatorBuilder.routes()
				  .route(p ->
				           p.path("/amitbank/accounts/**")
				           .filters(f -> f.rewritePath("/amitbank/accounts/(?<segment>.*)","/${segment}")
				        		   .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						   .uri("lb://ACCOUNTS"))
				  .route(p ->
		           p.path("/amitbank/loans/**")
		           .filters(f -> f.rewritePath("/amitbank/loans/(?<segment>.*)","/${segment}")
		        		   .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
				   .uri("lb://LOANS"))
				  .route(p ->
		           p.path("/amitbank/cards/**")
		           .filters(f -> f.rewritePath("/amitbank/cards/(?<segment>.*)","/${segment}")
		        		   .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
				   .uri("lb://CARDS"))
				  
				  
				  .build();
		
	}

}
