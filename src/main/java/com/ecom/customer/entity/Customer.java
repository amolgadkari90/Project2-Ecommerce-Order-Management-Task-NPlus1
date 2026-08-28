package com.ecom.customer.entity;

import com.ecom.orders.entity.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.ecom.audit.Audit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name ="customer")
public class Customer extends Audit {
	
	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto generate IDs 
	@Column(name = "customer_id") // SQL use _
	private Long id; // DB ID
	
	@Column(name = "customer_name")
	private String name;
	
	@Column(name = "customer_email")
	private String email;
	
	
	/*
	 * OneToMany ->One customer can have multiple orders
	 * mappedBy -> the Customer.orders relationship is mapped/controlled by the customer 
	 * field in the Order class
	 * CascadeType.ALL -> This is if we delete customer all orders belong to that customer 
	 * will be deleted
	 * 
	 * */
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	
	//@JsonIgnore -> this will not be included when we are sending customer object 
	//There will be separate API in Orders where client will give Customer ID and from that 
	//the orders related to particular customer can be fetched 
	//If we keep it here for every field on list will be serialized and deserialized at client end 
	//and data will be large 
	@JsonIgnore
	private List<Order> orders;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	


}
