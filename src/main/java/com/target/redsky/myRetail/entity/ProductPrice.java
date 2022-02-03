package com.target.redsky.myRetail.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Prashant Vaikole
 * Entity class for ProductPrice MongoDB collection
 * Stores ID, Product Cost & Currency Code
 *
 */


@Document(collection="ProductPrice")
public class ProductPrice {
	
	@Id	
	private long id;
	
	private BigDecimal cost;
	
	private String currencyCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	

}
