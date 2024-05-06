package com.fdmgroup.courierapp.model;

import java.util.Date;

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
	private String recipientFullName;
	private String recipientEmail;
	private String recipientPhoneNo;
	private String deliveryAddress;
	private String pickupAddress;
	private Date deliveryDate;
	private Date orderDate;
	private Date lastUpdated;
	private Date createdOn;

	@ManyToOne
	@JoinColumn(name = "FK_Courier_Id")
	private Courier courier;
	@OneToOne
	@JoinColumn(name = "FK_Parcel_Id")
	private Parcel parcel;
	@ManyToOne
	@JoinColumn(name = "FK_sender_Id")
	private Sender sender;

	public OrderItem() {
	}

	public OrderItem(long orderId, Status status, String recipientFullName, String recipientEmail,
					 String recipientPhoneNo, String deliveryAddress, String pickupAddress, Date deliveryDate, Date orderDate,
					 Date lastUpdated, Date createdOn, Courier courier, Parcel parcel, Sender sender) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.recipientFullName = recipientFullName;
		this.recipientEmail = recipientEmail;
		this.recipientPhoneNo = recipientPhoneNo;
		this.deliveryAddress = deliveryAddress;
		this.pickupAddress = pickupAddress;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.lastUpdated = lastUpdated;
		this.createdOn = createdOn;
		this.courier = courier;
		this.parcel = parcel;
		this.sender = sender;
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

	public String getRecipientFullName() {
		return recipientFullName;
	}

	public void setRecipientFullName(String recipientFullName) {
		this.recipientFullName = recipientFullName;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getRecipientPhoneNo() {
		return recipientPhoneNo;
	}

	public void setRecipientPhoneNo(String recipientPhoneNo) {
		this.recipientPhoneNo = recipientPhoneNo;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getPickupAddress() {
		return pickupAddress;
	}

	public void setPickupAddress(String pickupAddress) {
		this.pickupAddress = pickupAddress;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
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

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

}

enum Status {
	PROCESSING, READYFORPICKUP, ENROUTE, DELIVERED, FAILED, RETURNED
}
