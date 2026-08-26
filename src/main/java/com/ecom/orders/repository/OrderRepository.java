package com.ecom.orders.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecom.orders.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
