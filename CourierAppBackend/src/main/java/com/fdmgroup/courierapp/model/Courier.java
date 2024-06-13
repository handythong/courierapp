package com.fdmgroup.courierapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Courier {
	
	@Id
	@Column(name = "account_id")
	private Long accountId;
	
	private String fullName;
	private float vehicleCapacity;
	private Date lastUpdated;

	@OneToMany(mappedBy = "courier")
	private List<Trip> trips = new ArrayList<>();

	public Courier() {
	}

	public Courier(Long accountId, String fullName, float vehicleCapacity, Date lastUpdated, List<Trip> trips) {
		this.accountId = accountId;
		this.fullName = fullName;
		this.vehicleCapacity = vehicleCapacity;
		this.lastUpdated = lastUpdated;
		this.trips = trips;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public float getVehicleCapacity() {
		return vehicleCapacity;
	}

	public void setVehicleCapacity(float vehicleCapacity) {
		this.vehicleCapacity = vehicleCapacity;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<Trip> getOrderItemList() {
		return trips;
	}

	public void setOrderItemList(List<Trip> trips) {
		this.trips = trips;
	}
}
