package com.ecom.globalexceptionhandler.customexceptions;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3128299582171883222L;

	public CustomerNotFoundException(String message){
		super(message);
	}

}
