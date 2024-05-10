package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

@Entity
public class Parcel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcelId_IdSeq")
	@SequenceGenerator(name = "parcelId_IdSeq", sequenceName = "parcelId_IdSeq", allocationSize = 1, initialValue = 1)
	private long id;

	private float weight;
	private float length;
	private float width;
	private float height;
	private String description;

	@OneToOne(mappedBy = "parcel")
	private CustomerOrder customerOrder;

	public Parcel() {
	}

	public Parcel(long id, float weight, float length, float width, float height, String description, CustomerOrder customerOrder) {
		this.id = id;
		this.weight = weight;
		this.length = length;
		this.width = width;
		this.height = height;
		this.description = description;
		this.customerOrder = customerOrder;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public CustomerOrder getOrderItem() {
		return customerOrder;
	}

	public void setOrderItem(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}
}
