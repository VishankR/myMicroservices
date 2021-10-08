package com.vishank.microservices.springCurrencyExchangeService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishank.microservices.springCurrencyExchangeService.bean.CurrencyExchange;

public interface CurrencyExchangeRepositoryIF  extends JpaRepository<CurrencyExchange, Long>{
	public CurrencyExchange findByFromAndTo(String from, String to);
}
