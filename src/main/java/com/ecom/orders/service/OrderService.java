package com.ecom.orders.service;

import com.ecom.orders.controller.OrderController;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.customer.entity.Customer;
import com.ecom.customer.service.CustomerService;
import com.ecom.orders.dto.OrderRequest;
import com.ecom.orders.dto.OrderResponse;
import com.ecom.orders.entity.Order;
import com.ecom.orders.repository.OrderRepository;
import com.ecom.utility.OrderStatus;

@Service
public class OrderService {

	OrderRepository ordRepo;
	CustomerService custServ;

	// Constructor injection
	OrderService(OrderRepository _ordRepo, CustomerService _custServ) {
		this.ordRepo = _ordRepo;
		this.custServ = _custServ;
	}

	// Create order for a customer
	@Transactional
	public OrderResponse saveOrder(OrderRequest request) {

		System.out.println("OrderService.saveOrder()");

		// Get customer object from ID
		Customer customer = getCustomerById(request.getCustomerId());

		// Create Order Entity
		Order order = orderRequestToEntity(request, customer);

		// Save to DB
		Order savedOrder = ordRepo.save(order);

		// Map to response
		OrderResponse response = orderEntityToResponse(savedOrder);

		// return responses

		return response;

	}

	public void getAllOrdersForCustomer(Long customerId) {

		List<Order> orders = ordRepo.findAllByCustomerId(customerId);

		for (Order o : orders) {
			System.out.println(o.getId() + " " + o.getOrderNumber());
		}

	}

	public List<OrderResponse> getAllOrders() {
		// TODO Auto-generated method stub
		// Map to Order Response
		List<OrderResponse> reposnses = new ArrayList<OrderResponse>();

		
		//Iterable<Order> all = ordRepo.findAll();  //Problem ->Find all
		
		//List<Order> all = ordRepo.findAllOrdersWithCustomer(); //Solving problme using join fetch
		
		List<Order> all = ordRepo.findAllOrdersWithCustomerUsingEntityGraph(); //Solving problme using EntityGraph
		 
//		 for(Order o :all) {
//		 System.out.println(o.getId() + " " + o.getOrderNumber());
//		 o.getCustomer().getName();
//		 
//		  }		


		for (Order o : all) {

			OrderResponse response = orderEntityToResponse(o);
			reposnses.add(response);

		}

		return reposnses;

	}

	/********** UTILITY ***********/

	private OrderResponse orderEntityToResponse(Order savedOrder) {
		// TODO Auto-generated method stub
		OrderResponse response = new OrderResponse();

		response.setCreatedAt(savedOrder.getCreatedAt());
		response.setCustomer(savedOrder.getCustomer());
		response.setId(savedOrder.getId());
		response.setOrderDate(savedOrder.getOrderDate());
		response.setOrderNumber(savedOrder.getOrderNumber());
		response.setStatus(savedOrder.getStatus());
		response.setTotalAmount(savedOrder.getTotalAmount());
		response.setUpdatedAt(savedOrder.getUpdatedAt());

		return response;
	}

	private Order orderRequestToEntity(OrderRequest request, Customer customer) {
		// TODO Auto-generated method stub

		Order order = new Order();
		// Set customer
		order.setCustomer(customer);

		// Generate orderNumber
		String orderNumber = generateOrderId();
		order.setOrderNumber(orderNumber);

		// Set date
		order.setOrderDate(LocalDate.now());

		// set Status
		order.setStatus(OrderStatus.CREATED);

		// set amount
		order.setTotalAmount(request.getTotalAmount());

		return order;
	}

	private String generateOrderId() {
		int orderNum = (int) (Math.random() * 900000 + 100000); // Math.random() -> double return type -> typecast to
																// int
		String orderId = "OrderID" + orderNum;
		return orderId;
	}

	private Customer getCustomerById(Long customerId) {
		// TODO Auto-generated method stub
		Customer customer = custServ.findCustomerById(customerId);
		return customer;
	}

}
