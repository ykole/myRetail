package com.target.redsky.myRetail.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



/**
 * @author Prashant Vaikole
 *
 */

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	
	

		// handling global exception
		
	/*	@ExceptionHandler(Exception.class)
		public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
			ErrorResponse errorDetails = 
					new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}*/
	
	
		@ExceptionHandler(ProductPriceNotFound.class)
		public ResponseEntity<?> resourceNotFoundHandling(ProductPriceNotFound exception, WebRequest request){
			ErrorResponse errorDetails = 
					new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
		
		
		@ExceptionHandler(ExternalAPIException.class)
		public ResponseEntity<?> externalAPIExceptionHandling(ExternalAPIException exception, WebRequest request){
			ErrorResponse errorDetails = 
					new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		@ExceptionHandler(ProductDetailsNotFound.class)
		public ResponseEntity<?> handleProductDetailsNotFound(ExternalAPIException exception, WebRequest request){
			ErrorResponse errorDetails = 
					new ErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
			return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		}
		
		
	
	

}
