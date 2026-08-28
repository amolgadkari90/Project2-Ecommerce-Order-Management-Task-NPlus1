package com.ecom.orders.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecom.orders.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findAllByCustomerId(Long customerId);
	
	//Join Fetch -> to solve N+1 Problem
	@Query("SELECT o FROM Order o JOIN FETCH o.customer")
	List<Order> findAllOrdersWithCustomer();

	
	//using entity graph
	@EntityGraph(attributePaths = {"customer"})
	@Query("SELECT o FROM Order o") // This is needed bcoz using custom method name 
									//if findAll() would have used no need of this
	List<Order> findAllOrdersWithCustomerUsingEntityGraph();

}
