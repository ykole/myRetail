package com.target.redsky.myRetail.util;

import com.target.redsky.myRetail.dto.ProductPriceDTO;
import com.target.redsky.myRetail.dto.ProductPriceDetails;

/**
 * @author Prashant Vaikole
 *
 */

public class CustomValidation {
	
	public static String validateProductPriceDTO(ProductPriceDetails product)
	{
		StringBuilder error =new StringBuilder();
		if (product != null)
		{					
			if (product.getProductId() == 0 )				
			{
				error.append("Product ID is madantory.");
			}
			
			if(product.getProductPrice() == null)
			{
					error.append("Product Price is mandatory.");
			}
			else
			{				
				ProductPriceDTO productPrice = product.getProductPrice();
				if (productPrice.getValue() == null)
				{
					error.append("Product Price is mandatory field");				
				}
				else
				{
					try
					{
						if (productPrice.getValue().doubleValue() <= 0)
						{
							error.append("Product value can't be 0 or negative");
						}
					}
					catch(NullPointerException e)
					{
						error.append("Product value is mandatory field");
					}
				}				
						
			}				
			
		}
		return error.toString();
		
	}
	
	

}
