package com.fdmgroup.courierapp.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemId_IdSeq")
	@SequenceGenerator(name = "orderItemId_IdSeq", sequenceName = "orderItemId_IdSeq", allocationSize = 1, initialValue = 1)
	private long orderId;
	@Enumerated(EnumType.STRING)
	private Status status;
	private Date deliveryDate;
	private Date orderDate;
	private Date lastUpdated;

	@ManyToOne
	@JoinColumn(name = "FK_Courier_Id")
	private Courier courier;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_Parcel_Id")
	private Parcel parcel;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_Order_Sender_Id")
	private OrderSender orderSender;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_Order_Recipient_Id")
	private OrderRecipient orderRecipient;
	@ManyToOne
	@JoinColumn(name = "FK_Customer_Id")
	private Customer customer;

	public OrderItem() {
	}

	public OrderItem(long orderId, Status status, Date deliveryDate, Date orderDate, Date lastUpdated, Courier courier, Parcel parcel, OrderSender orderSender, OrderRecipient orderRecipient, Customer customer) {
		this.orderId = orderId;
		this.status = status;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.lastUpdated = lastUpdated;
		this.courier = courier;
		this.parcel = parcel;
		this.orderSender = orderSender;
		this.orderRecipient = orderRecipient;
		this.customer = customer;
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

	public OrderSender getOrderSender() {
		return orderSender;
	}

	public void setOrderSender(OrderSender orderSender) {
		this.orderSender = orderSender;
	}

	public OrderRecipient getOrderRecipient() {
		return orderRecipient;
	}

	public void setOrderRecipient(OrderRecipient orderRecipient) {
		this.orderRecipient = orderRecipient;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
