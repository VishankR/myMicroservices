package com.vishank.microservices.springCurrencyExchangeService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sampleapi")
	//@Retry(name="sample-api", fallbackMethod = "hardcodedResponse")
	@CircuitBreaker(name="default", fallbackMethod = "hardcodedResponse")
	@RateLimiter(name="sample-api")
	@Bulkhead(name="sample-api")
	public String sampleApi() {
		logger.info("Sample Call received.");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("https://localhost:8080/dummy-url", String.class);
		return forEntity.getBody();
	}
	public String hardcodedResponse(Exception ex) {
		return "fallback response";
	}
}
