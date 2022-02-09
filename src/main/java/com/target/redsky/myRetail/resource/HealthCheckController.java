package com.target.redsky.myRetail.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;import com.target.redsky.myRetail.service.APIService;
import com.target.redsky.myRetail.service.ProductDetailService;

/**
 * @author Prashant Vaikole
 *
 */


@RestController
@RequestMapping(value="/myRetail",produces = {MediaType.APPLICATION_JSON_VALUE})
public class HealthCheckController {

	@Autowired
	APIService apiService;
	
	@Autowired
	ProductDetailService productDetailService;
	
	@GetMapping("/healthcheck/{id}")
	public ResponseEntity<Object> getHealthCheckReport(@PathVariable long id) 
	{		
			
		if (apiService.checkHealth(id) &&  productDetailService.checkHealthDB(id))
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.internalServerError().build();
		}
		
		
	}
	
	@GetMapping("/healthcheckapi/{id}")
	public ResponseEntity<Object> getHealthCheckReportAPI(@PathVariable long id) 
	{		
			
		if (apiService.checkHealth(id))
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.internalServerError().build();
		}
		
		
	}
	
	@GetMapping("/healthcheckdb/{id}")
	public ResponseEntity<Object> getHealthCheckReportRepository(@PathVariable long id) 
	{		
			
		if (productDetailService.checkHealthDB(id))
		{
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.internalServerError().build();
		}
		
		
	}
}
