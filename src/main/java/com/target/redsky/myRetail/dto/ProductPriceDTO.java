package com.target.redsky.myRetail.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Prashant Vaikole
 * DTO class to receive data from Put/Post call and pass to Service or Repo
 *
 */

public class ProductPriceDTO {

	@NotNull
	@Positive
	@NotBlank
	@NotEmpty
	private BigDecimal value;
	
	@JsonProperty("currency_code")
	private String currencyCode;


	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public ProductPriceDTO(@NotNull @Positive BigDecimal value, String currencyCode) {
		super();
		this.value = value;
		this.currencyCode = currencyCode;
	}
	
	
	
}
