package com.target.redsky.myRetail.resource;

import java.math.BigDecimal;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.target.redsky.myRetail.dto.*;
import com.target.redsky.myRetail.entity.*;
import com.target.redsky.myRetail.exception.ProductPriceNotFound;
import com.target.redsky.myRetail.repository.*;
import com.target.redsky.myRetail.service.*;
import com.target.redsky.myRetail.util.*;

/**
 * @author Prashant Vaikole
 *
 */


@RestController
@RequestMapping(value="/myRetail",produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {
	
	@Autowired
	ProductPriceRepository productPriceRepository;
	
	@Autowired
	ProductDetailService productDetailService;
	
	
	
	@PostMapping("/products/")
	public ResponseEntity<Object> addProductPrice(@RequestBody ProductPriceDetails price)
	{		
		ProductPrice newProductPrice=new ProductPrice();
		newProductPrice.setId(price.getProductId());
		newProductPrice.setCost(price.getProductPrice().getValue());
		newProductPrice.setCurrencyCode(price.getProductPrice().getCurrencyCode());
		newProductPrice = productPriceRepository.save(newProductPrice);
		return ResponseEntity.ok().body(newProductPrice.toString());
	}
	
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getProductDetailsByID(@PathVariable long id) 
	{		
		
		ProductPriceDetails product = productDetailService.getProductbyID(id);
		if(product != null)
		{
			return ResponseEntity.ok().body(product);
		}
		else
		{
			return ResponseHandler.sendProductNotFound(id);
		}
		
		
	}
	
	@PutMapping("/products")
	public ResponseEntity<Object> updateProductPrice(@RequestBody ProductPriceDetails productDetails )
	{			
		
		String error = CustomValidation.validateProductPriceDTO(productDetails);
	
		if (error.trim().length()>0)
		{
			return ResponseHandler.sendFailurePriceUpdateError(error);
		}
		
		
			if (productDetailService.updateProductPrice(productDetails))
			{
				
				URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	                    .path("/{id}")
	                    .buildAndExpand(productDetails.getProductId())
	                    .toUri();
				
				//return ResponseEntity.ok().body(location);
				return ResponseHandler.sendUpdateSuccess(location);
				
			}
			else
			{
				return ResponseHandler.sendProductNotFound(productDetails.getProductId());
			}
				
			
	}	

}
