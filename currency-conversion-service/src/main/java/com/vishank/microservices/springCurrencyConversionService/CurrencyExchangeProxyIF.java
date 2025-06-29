package com.vishank.microservices.springCurrencyConversionService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vishank.microservices.springCurrencyConversionService.bean.CurrencyConversion;
/**
 * This interface is a proxy for the currency-exchange-service. It is using the Feign client to call the currency-exchange-service
 * and retrieve the exchange value from a given currency to another given currency.
 *
 * @author Vishank
 *
 */
//@FeignClient(name="currency-exchange-service", url="localhost:8000")
@FeignClient(name="currency-exchange-service")
public interface CurrencyExchangeProxyIF {
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
