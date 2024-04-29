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
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemId_IdSeq")
	@SequenceGenerator(name = "orderItemId_IdSeq", sequenceName = "orderItemId_IdSeq", allocationSize = 1, initialValue = 1)
	private long orderId;
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "FK_Courier_Id")
	private Courier courier;
	@OneToOne
	@JoinColumn(name = "FK_Parcel_Id")
	private Parcel parcel;
	@ManyToOne
	@JoinColumn(name = "FK_sender_Id")
	private User sender;
	@ManyToOne
	@JoinColumn(name = "FK_recipient_Id")
	private User recipient;

	public OrderItem(long orderId, Status status, Courier courier, Parcel parcel, User sender, User recipient) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.courier = courier;
		this.parcel = parcel;
		this.sender = sender;
		this.recipient = recipient;
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

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
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
