package com.ecom.orders.dto;

import java.math.BigDecimal;

public class OrderRequest {
		
	private Long customerId;	
	private BigDecimal totalAmount;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}	
}
