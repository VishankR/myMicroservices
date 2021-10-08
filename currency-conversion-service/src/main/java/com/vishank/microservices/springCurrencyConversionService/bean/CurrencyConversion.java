package com.vishank.microservices.springCurrencyConversionService.bean;

import java.math.BigDecimal;

public class CurrencyConversion {
	private Long id;
	private String from;
	private String to;
	private BigDecimal quantity;
	private BigDecimal conversionMultiple;
	private BigDecimal totalCalculatedQuantity;
	private String envDetails;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public CurrencyConversion() {
		super();
	}
	public CurrencyConversion(Long id, String from, String to, BigDecimal quantity, BigDecimal conversionMultiple,
			BigDecimal totalCalculatedQuantity, String envDetails) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.conversionMultiple = conversionMultiple;
		this.totalCalculatedQuantity = totalCalculatedQuantity;
		this.envDetails = envDetails;
	}
	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}
	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}
	public BigDecimal getTotalCalculatedQuantity() {
		return totalCalculatedQuantity;
	}
	public void setTotalCalculatedQuantity(BigDecimal totalCalculatedQuantity) {
		this.totalCalculatedQuantity = totalCalculatedQuantity;
	}
	public String getEnvDetails() {
		return envDetails;
	}
	public void setEnvDetails(String envDetails) {
		this.envDetails = envDetails;
	}
	
}
