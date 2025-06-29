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

/**
 * @RateLimiter:
 * 		- It is used to control the number of concurrent calls to a particular service.
 * 		- It can be used to prevent a service from being overwhelmed by too many requests.
 * 		- The underlying implementation uses a semaphore to limit the number of concurrent calls.
 *
 * @Bulkhead:
 * 		- It is used to isolate a service from other services.
 * 		- It can be used to prevent a service from affecting other services in case of a failure.
 * 		- The underlying implementation uses a thread pool to isolate the service.
 * 		- The thread pool is used to execute the service and if the service fails, the thread pool is used to limit the number of failures.
 * 		- The thread pool is also used to limit the number of concurrent calls to the service.
 */
/**
 * This class is a controller for the circuit breaker example.
 * It exposes a single endpoint GET /sampleapi which makes a call to a dummy URL.
 * The call is decorated with the following resilience4j annotations:
 * 	- @CircuitBreaker: This annotation is used to specify the name of the circuit breaker.
 * 							The circuit breaker will be used to detect when the backend service is not responding.
 * 							When the circuit breaker is open, all incoming requests will be rejected and the fallbackMethod will be called.
 * 	- @RateLimiter: This annotation is used to specify the name of the rate limiter.
 * 						The rate limiter will be used to limit the number of requests to the backend service.
 * 						When the rate limit is exceeded, the fallbackMethod will be called.
 * 	- @Bulkhead: This annotation is used to specify the name of the bulkhead.
 * 					The bulkhead will be used to isolate the backend service.
 * 					When the bulkhead is full, all incoming requests will be rejected and the fallbackMethod will be called.
 *
 * The fallbackMethod is hardcodedResponse which returns a static string "fallback response".
 *
 * The purpose of this class is to demonstrate how to use resilience4j annotations to protect a service from backend failures.
 */
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
