package com.vishank.microservices.springCurrencyExchangeService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vishank.microservices.springCurrencyExchangeService.bean.CurrencyExchange;
import com.vishank.microservices.springCurrencyExchangeService.repository.CurrencyExchangeRepositoryIF;

@RestController
public class CurrencyExchangeController {
	@Autowired
	Environment env;
	@Autowired
	CurrencyExchangeRepositoryIF currExgRepo;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange = currExgRepo.findByFromAndTo(from, to);
		currencyExchange.setEnvDetails(env.getProperty("local.server.port"));
		return currencyExchange;
	}
}
