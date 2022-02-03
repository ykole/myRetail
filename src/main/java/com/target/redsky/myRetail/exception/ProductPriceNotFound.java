package com.target.redsky.myRetail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Prashant Vaikole
 *
 */


public class ProductPriceNotFound extends RuntimeException {

	/**
	 * SUID
	 */
	private static final long serialVersionUID = 1L;

	public ProductPriceNotFound(String message) 
	{
		super(message);
		
	}
}