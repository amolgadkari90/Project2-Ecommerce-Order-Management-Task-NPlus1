package com.ecom.customer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.customer.dto.CustomerRequest;
import com.ecom.customer.dto.CustomerResponse;
import com.ecom.customer.service.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	CustomerService custServ;

	CustomerController(CustomerService _custServ) {
		this.custServ = _custServ;
	}

	@PostMapping("/create")
	public ResponseEntity<CustomerResponse> saveCustomer(@RequestBody CustomerRequest custRequest) {

		// Service layer
		CustomerResponse savedResponse = custServ.saveCustomer(custRequest);

		// Retuurn -> ResponseEntity to client
		return ResponseEntity.status(HttpStatus.CREATED).body(savedResponse);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {

		// Service layer
		CustomerResponse customerById = custServ.getCustomerById(id);

		// Return to client
		return ResponseEntity.status(HttpStatus.OK).body(customerById);
	}

	// Get all customers
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
		
		List<CustomerResponse> listOfCustomers = custServ.getAllCustomers();
		
		return ResponseEntity.status(HttpStatus.OK).body(listOfCustomers);

	}

}
