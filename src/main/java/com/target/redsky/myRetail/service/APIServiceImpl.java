package com.target.redsky.myRetail.service;

import java.util.HashMap;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.redsky.myRetail.exception.ExternalAPIException;

@Service("apiServiceImpl")
public class APIServiceImpl implements APIService{
	
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
	
	public boolean checkHealth(long id)
	{
			
		 	HttpHeaders headers = new HttpHeaders();		     
		    headers.setContentType(MediaType.APPLICATION_JSON);		      
		    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiEndPoint).queryParam(apiEndPointKeyName.toString(),apiEndPointKeyValue.toString()).queryParam("tcin", id);		
		      
		    try
			{
				HttpEntity<String> httpEntity = new HttpEntity<String>(apiEndPoint, headers);
			    ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity,String.class);		    
			    			
			    if (response.getStatusCode() == HttpStatus.OK)
			    {
					return true;
			    }	  
		   	
			}
		    catch (HttpClientErrorException hre) 
		    {
		    	   if (hre.getStatusCode() == HttpStatus.NOT_FOUND)
		    	   {
		    		   return true;
		    	   }
		    	   else if(hre.getStatusCode() ==HttpStatus.UNAUTHORIZED)
		    	   {
		    		   return false;
		    	   }
		    }		 
		    catch(Exception e)
		    {			
			
		    	return false;
		    }
		    return false;
		
	}
	
	
	public String getProductName(long id)
	{
		
		if (id == 0)
		{
			return "";
		}
			
		    HttpHeaders headers = new HttpHeaders();		     
		    headers.setContentType(MediaType.APPLICATION_JSON);		      
		    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiEndPoint).queryParam(apiEndPointKeyName.toString(),apiEndPointKeyValue.toString()).queryParam("tcin", id);		
		      
		    try
			{
			HttpEntity<String> httpEntity = new HttpEntity<String>(apiEndPoint, headers);
		    ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity,String.class);
		    
		    			
		    if (response.getStatusCode() == HttpStatus.OK)
		    {
		    	return parseProductName(objectMapper.readValue(response.getBody(), Map.class));
				/*Map<String, Map> resultMap = objectMapper.readValue(response.getBody(), Map.class);
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
				}	*/			
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
	
		//return "";
		
	}
	
	
	public String parseProductName(Map<String,Map> resultMap)
	{		
		String productName="";
		try
		{			
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
							productName=prodDescrMap.get("title");									
						}
					}
				}
			}
			
		}
		catch(Exception e)
		{
			return "";
		}
		
		return productName;
	}

}
