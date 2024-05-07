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

	@OneToOne
	@JoinColumn(name = "PK_FK_account_Id")
	private Account account;
	
	private String fullName;
	private int vehicleCapacity;
	private Date lastUpdated;
	
	@OneToMany(mappedBy = "courier")
	private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

	public Courier() {
	}

	public Courier(Account account, String fullName, int vehicleCapacity, Date lastUpdated,
				   List<OrderItem> orderItemList) {
		super();
		this.account = account;
		this.fullName = fullName;
		this.vehicleCapacity = vehicleCapacity;
		this.lastUpdated = lastUpdated;
		this.orderItemList = orderItemList;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
