package com.fdmgroup.courierapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderId_IdSeq")
	@SequenceGenerator(name = "orderId_IdSeq", sequenceName = "orderId_IdSeq", allocationSize = 1, initialValue = 1)
	private long orderId;
	
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "FK_courier")
	private Courier courier;
	@OneToOne
	private Parcel parcel;
	
//	private long recipientId;
//	private long senderId;
	
	public Order(long orderId, Status status, Courier courier, Parcel parcel) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.courier = courier;
		this.parcel = parcel;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public Parcel getParcel() {
		return parcel;
	}

	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}
}

enum Status {
	PROCESSING,
	READYFORPICKUP,
	ENROUTE,
	DELIVERED,
	FAILED,
	RETURNED
}
