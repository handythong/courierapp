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

	private int weight;
	private String description;

	@OneToOne
	private OrderItem orderItem;

	public Parcel(long parcelId, int weight, String description, OrderItem orderItem) {
		super();
		this.parcelId = parcelId;
		this.weight = weight;
		this.description = description;
		this.orderItem = orderItem;
	}

	public long getParcelId() {
		return parcelId;
	}

	public void setParcelId(long parcelId) {
		this.parcelId = parcelId;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
}
