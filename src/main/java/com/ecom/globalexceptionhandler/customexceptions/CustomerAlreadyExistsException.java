package com.ecom.globalexceptionhandler.customexceptions;

public class CustomerAlreadyExistsException extends RuntimeException {
	
	public CustomerAlreadyExistsException(String message) {
        super(message);
    }	

}
