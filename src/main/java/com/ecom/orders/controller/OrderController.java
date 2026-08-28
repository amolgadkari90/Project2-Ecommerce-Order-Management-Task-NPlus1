package com.ecom.orders.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.orders.dto.OrderRequest;
import com.ecom.orders.dto.OrderResponse;
import com.ecom.orders.service.OrderService;

@RestController
@RequestMapping("orders")
public class OrderController {
	
	OrderService ordServ;
	
	OrderController(OrderService _ordServ){
		this.ordServ = _ordServ;
	}
	
	//create order 
	
	@PostMapping("/create")
	public ResponseEntity<OrderResponse> saveOrder( @RequestBody OrderRequest request){
		
		System.out.println("OrderController.saveOrder()");
		
		//Call service layer
		OrderResponse savedOrder = ordServ.saveOrder(request);
		
		//Return reposnse entity
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
		
	}
	
	
	@GetMapping("/getAllOrders/{customerId}")
	public ResponseEntity<String> getAllOrdersForCustomer( @PathVariable Long customerId) {
		ordServ.getAllOrdersForCustomer(customerId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Accepted");
		
	}
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<OrderResponse>> getAllOrders( ) {
		List<OrderResponse> allOrders = ordServ.getAllOrders();
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allOrders);
		
	}
	
	
	

}
