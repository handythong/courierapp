package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

@Entity
public class Parcel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcelId_IdSeq")
	@SequenceGenerator(name = "parcelId_IdSeq", sequenceName = "parcelId_IdSeq", allocationSize = 1, initialValue = 1)
	private long parcelId;

	private float weight;
	private float length;
	private float width;
	private float height;
	private String description;

	@OneToOne(mappedBy = "parcel")
	private OrderItem orderItem;

	public Parcel() {
	}

	public Parcel(long parcelId, float weight, float length, float width, float height, String description, OrderItem orderItem) {
		this.parcelId = parcelId;
		this.weight = weight;
		this.length = length;
		this.width = width;
		this.height = height;
		this.description = description;
		this.orderItem = orderItem;
	}

	public long getParcelId() {
		return parcelId;
	}

	public void setParcelId(long parcelId) {
		this.parcelId = parcelId;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
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
