package com.target.redsky.myRetail.entity;

import java.math.BigDecimal;

/**
 * @author Prashant Vaikole
 *
 */

public class Cost {
	
	private BigDecimal value;
	
	private String currencyCode;
	
		

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getCurrencyCode() 
	{
		return currencyCode;
	}
	
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	
	

}
