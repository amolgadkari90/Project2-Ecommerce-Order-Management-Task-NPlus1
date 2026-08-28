package com.ecom.orders.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import com.ecom.audit.Audit;
import com.ecom.customer.entity.Customer;
import com.ecom.utility.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders") // order is keyword in SQL so chnage the name to Orders
public class Order extends Audit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;
	
	@Column(name ="order_number")
	private String orderNumber;
	
	@CreatedDate
	@Column(name = "order_date")
	private LocalDate orderDate;
	
	@Column(name = "total_amount")
	private BigDecimal totalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private OrderStatus status;
	
	// Order
	@ManyToOne(fetch = FetchType.LAZY) // Each order has one customer fetch -> Lazy so when
										// asked then only fetch customer object
	@JoinColumn(name = "customer_id") 
	// customer_id -> same field name in DB 
	//and this must be PrimaryKey in Customer Table
	//Use the customer_id column in the orders table to store the relationship to Customer.
	private Customer customer;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}		
}
