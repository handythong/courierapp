package com.fdmgroup.courierapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Courier {
	
	@Id
	@Column(name = "PK_FK_account_Id")
	private Long accountId;
	
	private String fullName;
	private int vehicleCapacity;
	private Date lastUpdated;
	
	@OneToMany(mappedBy = "courier")
	private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

	public Courier() {
	}

	public Courier(Long accountId, String fullName, int vehicleCapacity, Date lastUpdated, List<OrderItem> orderItemList) {
		this.accountId = accountId;
		this.fullName = fullName;
		this.vehicleCapacity = vehicleCapacity;
		this.lastUpdated = lastUpdated;
		this.orderItemList = orderItemList;
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

	public int getVehicleCapacity() {
		return vehicleCapacity;
	}

	public void setVehicleCapacity(int vehicleCapacity) {
		this.vehicleCapacity = vehicleCapacity;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
}
