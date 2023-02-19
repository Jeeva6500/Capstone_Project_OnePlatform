package com.natwest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.natwest.filter.APIFilter;

@Configuration
public class ApiGatewayConfiguration {
	

	@Autowired
	private APIFilter apiFilter;


	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
					.route(r -> r.path("/api/user/**") 
							.uri("lb://user-api"))
					
					.route(r -> r.path("/api/accounts/**")
							.filters(f -> f.filters(apiFilter))
							.uri("http://localhost:8084"))
					
					.route(r -> r.path("/api/transaction/**")
							.filters(f -> f.filters(apiFilter))
							.uri("http://localhost:8085"))
					
					.route(r -> r.path("/api/card/**")
							.filters(f -> f.filters(apiFilter))
							.uri("http://localhost:8086"))
					
					.route(r -> r.path("/login")
							.uri("http://localhost:8082"))
					.build();
	}
	
	
}

