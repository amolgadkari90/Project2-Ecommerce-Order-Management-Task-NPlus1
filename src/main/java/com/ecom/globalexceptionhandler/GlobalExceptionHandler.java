package com.ecom.globalexceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.globalexceptionhandler.customexceptions.CustomerAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<String> handleCustomerAlreadyExists(CustomerAlreadyExistsException ex){
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		
	}

}
