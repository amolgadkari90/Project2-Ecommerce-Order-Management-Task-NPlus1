package com.ecom.customer.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.customer.dto.CustomerRequest;
import com.ecom.customer.dto.CustomerResponse;
import com.ecom.customer.entity.Customer;
import com.ecom.customer.repository.CustomerRepository;
import com.ecom.globalexceptionhandler.customexceptions.CustomerAlreadyExistsException;
import com.ecom.globalexceptionhandler.customexceptions.CustomerNotFoundException;
import com.ecom.utility.ErrorMessage;

@Service
public class CustomerService {
	
	//Constructor based injection
	final CustomerRepository custRepo;

	CustomerService(CustomerRepository _custRepo) {
		this.custRepo = _custRepo;
	}
	
	/*Create customer
	 * Get all customers
	 * Get customer by ID
	 * */
	
	//Create customer
	
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
		CustomerResponse response =  custEntityToResponse(savedCustomer);
		
		//return response
		return response;	
		
	}
	
	//Get customer by ID
	
	@Transactional
	public CustomerResponse getCustomerById(Long customerId){
		//Find customer
		Customer customer = findCustomerById(customerId);
	              
	   //Map Entity -> Response
		CustomerResponse response =  custEntityToResponse(customer);
		
		return response;
	}
	
	//Get all customers
	public List<CustomerResponse> getAllCustomers(){
		
		Iterable<Customer> allCustomers = custRepo.findAll();
		List<CustomerResponse> customerList = new ArrayList<CustomerResponse>();
		for(Customer customer: allCustomers) {
			CustomerResponse response = custEntityToResponse(customer);
			customerList.add(response);			
		}		
		
		return customerList;		
	}
	
	
	/*Utility methods*/
	
	public Customer findCustomerById(Long customerId){
		
		//if customer not found throw exception
		Customer customer = custRepo.findById(customerId)
	            .orElseThrow(() ->
	                new CustomerNotFoundException(
	                    ErrorMessage.CUSTOMER_NOT_FOUND.toString()
	                )
	            );
		return customer;
		
	}
	
	private CustomerResponse custEntityToResponse(Customer savedCustomer) {
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
