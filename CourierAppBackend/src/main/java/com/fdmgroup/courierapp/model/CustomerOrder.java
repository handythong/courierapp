package com.fdmgroup.courierapp.model;

import java.time.LocalDateTime;
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
	private LocalDateTime deliveryDate;
	private LocalDateTime orderDate;
	private LocalDateTime lastUpdated;
	private String paymentReference;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "customerOrder", cascade = CascadeType.ALL)
	private List<Status> statuses = new ArrayList<>();
	@OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
	private List<Trip> trips = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "parcel_id")
	private Parcel parcel;
	@OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL)
	private List<Party> parties = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	public CustomerOrder() {
	}

	public CustomerOrder(long id, LocalDateTime deliveryDate, LocalDateTime orderDate, LocalDateTime lastUpdated, List<Status> statuses, List<Trip> trips, Parcel parcel, List<Party> parties, Customer customer, String paymentReference) {
		this.id = id;
		this.deliveryDate = deliveryDate;
		this.orderDate = orderDate;
		this.lastUpdated = lastUpdated;
		this.statuses = statuses;
		this.trips = trips;
		this.parcel = parcel;
		this.parties = parties;
		this.customer = customer;
		this.paymentReference = paymentReference;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Parcel getParcel() {
		return parcel;
	}

	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}

	public List<Party> getParties() {
		return parties;
	}

	public void setParties(List<Party> parties) {
		this.parties = parties;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPaymentReference() {
		return paymentReference;
	}

	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public void appendStatus(Status status) {
		this.statuses.add(status);
	}

	public void appendTrip(Trip trip) {
		this.trips.add(trip);
	}

	public void replaceParty(Party sender, Party recipient) {
		List<Party> newParties = new ArrayList<>();
		newParties.add(sender);
		newParties.add(recipient);
		this.parties = newParties;
	}
}
