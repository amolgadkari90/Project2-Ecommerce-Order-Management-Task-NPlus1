package com.ecom.audit;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

//This is not entity but the entity class extending this will add this class fields to table column 
@MappedSuperclass 
public abstract class Audit {
	
	/*
	 * Why abstract class?
	 * Its not actual object in logic -> we don't need object of this hence no normal class
	 * if interface the fields will be public static final by default 
	 * if that then we will not be able to change it hence in interface 
	 * 
	 * */
	
	/*
	 * why protected? -> Access to the classes within package or extending class 	
	 * */
	
	@CreationTimestamp
	@Column(name = "created_at")
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

}
