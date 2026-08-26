package com.ecom.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.globalexceptionhandler.customexceptions.CustomerAlreadyExistsException;
import com.ecom.globalexceptionhandler.customexceptions.CustomerNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<String> handleCustomerAlreadyExists(CustomerAlreadyExistsException ex){
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handlerCustomerNotFoundException(CustomerNotFoundException ex){
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		
	}

}
