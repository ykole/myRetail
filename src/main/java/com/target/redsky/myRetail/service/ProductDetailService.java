package com.target.redsky.myRetail.service;


import com.target.redsky.myRetail.dto.*;




/**
 * @author Prashant Vaikole
 *
 */

public interface ProductDetailService {

	public String getProductName(long id) ;
	
	public ProductPriceDetails getProductbyID(long id);
	
	public boolean updateProductPrice(ProductPriceDetails priceDetails);
	
	
	
}
