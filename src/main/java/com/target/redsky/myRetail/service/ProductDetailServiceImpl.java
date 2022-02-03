package com.target.redsky.myRetail.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.redsky.myRetail.entity.*;
import com.target.redsky.myRetail.exception.ExternalAPIException;
import com.target.redsky.myRetail.exception.ProductDetailsNotFound;
import com.target.redsky.myRetail.exception.ProductPriceNotFound;
import com.target.redsky.myRetail.dto.*;
import com.target.redsky.myRetail.repository.ProductPriceRepository;


/**
 * @author Prashant Vaikole
 *
 */

@Service("productDetailsServiceImpl")
public class ProductDetailServiceImpl implements ProductDetailService {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${ProductAPIEndPoint}")
	String apiEndPoint;
	
	@Value("${ProductAPIEndPoint.keyValue}")
	String apiEndPointKeyValue;
	
	@Value("${ProductAPIEndPoint.keyName}")
	String apiEndPointKeyName;
	
	@Autowired
	ProductPriceRepository productPriceRepository;
	
	
	public String getProductName(long id)
	{
		
		if (id == 0)
		{
			return "";
		}
			
		    HttpHeaders headers = new HttpHeaders();
		     // headers.set(apiEndPointKeyName, apiEndPointKeyValue);		     
		    headers.setContentType(MediaType.APPLICATION_JSON);		      
		    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiEndPoint).queryParam(apiEndPointKeyName.toString(),apiEndPointKeyValue.toString()).queryParam("tcin", id);		
		      
		    try
			{
			HttpEntity<String> httpEntity = new HttpEntity<String>(apiEndPoint, headers);
		    ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity,String.class);
		    
		    			
		    if (response.getStatusCode() == HttpStatus.OK)
		    {
				Map<String, Map> resultMap = objectMapper.readValue(response.getBody(), Map.class);
				if (resultMap.containsKey("data"))
				{
					Map<String,Map> dataMap = resultMap.get("data");
					if (dataMap.containsKey("product"))
					{
						Map<String,Map> productMap = dataMap.get("product");
						if (productMap.containsKey("item"))
						{
							Map<String,Map> itemMap = productMap.get("item");
							if (itemMap.containsKey("product_description"))
							{
								Map<String,String> prodDescrMap = itemMap.get(("product_description"));
								return prodDescrMap.get("title");
										
							}
						}
					}
				}				
		    }
		  
		    else
		    {		    
		    	throw new ExternalAPIException("Exception while retrieving data through third party API for Product "+id);
		    }
					
		
		}
		catch(Exception e)
		{			
			
			throw new ExternalAPIException("Exception while retrieving data through third party API for Product "+id);
		}
	
		return "";
		
	}
	
		public ProductPriceDetails getProductbyID(long id) 
		{
			
			
			String productName = getProductName(id);
			ProductPrice price = productPriceRepository.findById(id).orElseThrow(() ->new ProductPriceNotFound("Product Price not found in database for " +String.valueOf(id)));
			
					
			if (price != null && (!productName.trim().equals("")))
			{
				return new  ProductPriceDetails(price.getId(),productName, new ProductPriceDTO(price.getCost(),price.getCurrencyCode()));				
			}
			else
			{
				return null;
			}		
			
			
		}
		
		
		public boolean updateProductPrice(ProductPriceDetails priceDetails) 
		{
			
		   ProductPrice currentPrice = productPriceRepository.findById(priceDetails.getProductId()).orElseThrow(() ->new ProductPriceNotFound("Product Price not found in database for " +String.valueOf(priceDetails.getProductId())));
		   if (currentPrice != null)
		   {
			   currentPrice.setCost(priceDetails.getProductPrice().getValue());
			   currentPrice.setCurrencyCode(priceDetails.getProductPrice().getCurrencyCode());
			   productPriceRepository.save(currentPrice);
			   return true;
		   }
		   else
		   {
			   return false;
		   }
		   
			
		}
		
	
	

}
