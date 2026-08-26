package com.ecom.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.customer.dto.CustomerRequest;
import com.ecom.customer.dto.CustomerResponse;
import com.ecom.customer.entity.Customer;
import com.ecom.customer.repository.CustomerRepository;
import com.ecom.globalexceptionhandler.customexceptions.CustomerAlreadyExistsException;
import com.ecom.utility.ErrorMessage;

@Service
public class CustomerService {
	
	//Constructor based injection
	final CustomerRepository custRepo;

	CustomerService(CustomerRepository _custRepo) {
		this.custRepo = _custRepo;
	}
	
	/*
	 * Create customer
	 * Get all customers
	 * Get customer by ID
	 * 
	 * */
	
	@Transactional
	public CustomerResponse saveCustomer(CustomerRequest custRequest ){
		
		//Check if customer is already present in DB -> Unique email
		
		if(ifCustomerExist(custRequest.getEmail())) {			
			//Throw global exception		
			
			System.out.println("CustomerService.saveCustomer() -> Exception");
			throw new CustomerAlreadyExistsException(ErrorMessage.CUSTOMER_ALREADY_EXISTS.toString());
			
		}
		
		//Map request to persistant object -> Request -> Entity
		Customer customer = custRequestToEntity(custRequest);
		
		//Save to DB
		Customer savedCustomer = custRepo.save(customer);
				
		//Map Entity -> Response
		CustomerResponse response =  custEntityToRequest(savedCustomer);
		
		//return response
		return response;	
		
	}
	
	/*Utility methods*/
	
	private CustomerResponse custEntityToRequest(Customer savedCustomer) {
		// TODO Auto-generated method stub
		CustomerResponse response = new CustomerResponse();
		
		response.setId(savedCustomer.getId());
		response.setName(savedCustomer.getName());
		response.setEmail(savedCustomer.getEmail());
		response.setCreatedAt(savedCustomer.getCreatedAt());
		response.setUpdatedAt(savedCustomer.getUpdatedAt());
			
		return response;
	}

	private Customer custRequestToEntity(CustomerRequest custRequest) {
		// TODO Auto-generated method stub
		
		Customer customer = new Customer();
		
		customer.setName(custRequest.getName());
		customer.setEmail(custRequest.getEmail());
				
		return customer;
	}

	//check duplicate customer 
	private boolean ifCustomerExist(String email){
		return custRepo.existsByEmail(email);			
	}

}
