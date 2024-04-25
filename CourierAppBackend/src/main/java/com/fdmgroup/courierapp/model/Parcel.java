package com.fdmgroup.courierapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Parcel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcelId_IdSeq")
	@SequenceGenerator(name = "parcelId_IdSeq", sequenceName = "parcelId_IdSeq", allocationSize = 1, initialValue = 1)
	private long parcelId;
	
	private Size size;
	private String description;
	
	@OneToOne
	private Order order;
	
	public Parcel(long parcelId, Size size, String description, Order order) {
		super();
		this.parcelId = parcelId;
		this.size = size;
		this.description = description;
		this.order = order;
	}

	public long getParcelId() {
		return parcelId;
	}
	
	public void setParcelId(long parcelId) {
		this.parcelId = parcelId;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}

enum Size {
	SMALL,
	MEDIUM,
	LARGE
}
