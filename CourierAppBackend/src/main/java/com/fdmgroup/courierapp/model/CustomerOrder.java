package com.fdmgroup.courierapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class CustomerOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CustomerOrderId_IdSeq")
	@SequenceGenerator(name = "CustomerOrderId_IdSeq", sequenceName = "CustomerOrderId_IdSeq", allocationSize = 1, initialValue = 1)
	private long id;
	private Date deliveryDate;
	private Date orderDate;
	private Date lastUpdated;

	@OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
	private List<Status> status = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "courier_id")
	private Courier courier;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parcel_id")
	private Parcel parcel;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "sender_id")
	private Sender sender;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recipient_id")
	private Recipient recipient;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public CustomerOrder() {
	}

	public CustomerOrder(long id, Date deliveryDate, Date orderDate, Date lastUpdated, List<Status> status, Courier courier, Parcel parcel, Sender sender, Recipient recipient, Customer customer) {
		this.id = id;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.lastUpdated = lastUpdated;
		this.status = status;
		this.courier = courier;
		this.parcel = parcel;
		this.sender = sender;
		this.recipient = recipient;
		this.customer = customer;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
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

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void appendStatus(Status status) {
		this.status.add(status);
	}
}
