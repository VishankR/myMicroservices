package com.vishank.microservices.springApiGateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is a configuration class for the Spring Cloud Gateway.
 * It defines the routes that are exposed by the API Gateway.
 * For example, it will route any requests to /currency-exchange/** to the currency-exchange-service
 * and any requests to /currency-conversion/** to the currency-conversion-service.
 * The "lb" prefix to the url is a special string that tells Spring Cloud to use the load balancer
 * to resolve the service name to an actual host and port.
 */
@Configuration
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		//Function<PredicateSpec, Buildable<Route>> routeFunction = p -> p.path("/get").uri("http://httpbin.org:80");
		return builder.routes()
				.route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange-service"))
				.route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion-service"))
				.route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion-service"))
				
			.build();
	}
}
