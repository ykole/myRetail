package com.target.redsky.myRetail.util;

import java.net.URI;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import com.target.redsky.myRetail.dto.ProductPriceDetails;

/**
 * @author Prashant Vaikole
 * Custom response handler to send customize response 
 *
 */

public class ResponseHandler {
		
	public static ResponseEntity<Object> sendFailurePriceUpdate(long id)
	{
		Map<String,Object> map= new HashMap<String, Object>();	
		map.put("TimeStamp", new Timestamp(new Date().getTime()).toString());
		map.put("Error","Product Price can't be updated to 0 or negative");
		ResponseEntity<Object> res = new ResponseEntity<Object>(map,HttpStatus.NOT_ACCEPTABLE);
		
		return res;
	}
	
	public static ResponseEntity<Object> sendFailurePriceUpdateError(String error)
	{
		Map<String,Object> map= new HashMap<String, Object>();	
		map.put("TimeStamp", new Timestamp(new Date().getTime()).toString());
		map.put("Error",error);
		ResponseEntity<Object> res = new ResponseEntity<Object>(map,HttpStatus.NOT_ACCEPTABLE);
		
		return res;
	}
	
	public static ResponseEntity<Object> sendProductNotFound(long id)
	{
		Map<String,Object> map= new HashMap<String, Object>();	
		map.put("TimeStamp", new Timestamp(new Date().getTime()).toString());
		map.put("Error","Product not found");
		ResponseEntity<Object> res = new ResponseEntity<Object>(map,HttpStatus.NOT_FOUND);
		
		return res;
	}
	
	public static ResponseEntity<Object> sendMethodArgumentException(String message)
	{
		Map<String,Object> map= new HashMap<String, Object>();	
		map.put("TimeStamp", new Timestamp(new Date().getTime()).toString());
		map.put("Error",message);
		ResponseEntity<Object> res = new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		
		return res;
	}
	

	public static ResponseEntity<Object> sendUpdateSuccess(URI uri)
	{
		Map<String,Object> map= new HashMap<String, Object>();	
		map.put("TimeStamp", new Timestamp(new Date().getTime()).toString());
		map.put("URI",uri.getPath());
		map.put("Result", "Product Price has been updated");
		ResponseEntity<Object> res = new ResponseEntity<Object>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		
		return res;
	}


}
