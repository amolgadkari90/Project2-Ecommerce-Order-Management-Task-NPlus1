package com.ecom.globalexceptionhandler.customexceptions;

public class CustomerAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 7280771853265606476L;

	public CustomerAlreadyExistsException(String message) {
        super(message);
    }	

}
