package com.target.redsky.myRetail.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * @author Prashant Vaikole
 * DTO class to receive data from Put/Post call and pass to Service or Repo
 *
 */
public class ProductPriceDetails {
	
	@JsonProperty("id")
	private long productId;
	
	@JsonProperty("name")
	private String productName;
	
	@JsonProperty("current_price")
	private ProductPriceDTO productPrice;

	
	
	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductPriceDTO getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(ProductPriceDTO productPrice) {
		this.productPrice = productPrice;
	}

	public ProductPriceDetails(@NotNull long productId, String productName, ProductPriceDTO productPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	

	
}
