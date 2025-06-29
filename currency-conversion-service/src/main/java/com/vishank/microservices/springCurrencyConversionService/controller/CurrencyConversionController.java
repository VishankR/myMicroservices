package com.vishank.microservices.springCurrencyConversionService.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.vishank.microservices.springCurrencyConversionService.CurrencyExchangeProxyIF;
import com.vishank.microservices.springCurrencyConversionService.bean.CurrencyConversion;

@RestController
public class  CurrencyConversionController {
	@Autowired
	Environment env;
	@Autowired
	CurrencyExchangeProxyIF proxy;
	/**
	 * This method is supposed to take in 3 parameters: from, to and quantity.
	 * It is supposed to call the currency exchange service to get the conversion multiple
	 * and then calculate the total value to be converted. It is supposed to return a
	 * CurrencyConversion object with the from currency, to currency, quantity, conversion
	 * multiple and the total calculated amount.
	 */
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		HashMap<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		String port = env.getProperty("local.server.port");
		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class, uriVariables);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(), 
				quantity.multiply(currencyConversion.getConversionMultiple()), port + " Rest template");
	}
	
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		//String port = env.getProperty("local.server.port");
		CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(), 
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvDetails()+ " Feign");
	}
}
