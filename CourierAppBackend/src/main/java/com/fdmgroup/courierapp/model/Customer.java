package com.fdmgroup.courierapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {

	@Id
	@Column(name = "account_id")
	private Long accountId;

	private String fullName;
	private String email;
	private String phoneNo;
	private Date lastUpdated;

	@OneToMany(mappedBy = "customer")
	private List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();

	public Customer() {
	}

	public Customer(Long accountId, String fullName, String email, String phoneNo, Date lastUpdated, List<CustomerOrder> customerOrderList) {
		this.accountId = accountId;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.lastUpdated = lastUpdated;
		this.customerOrderList = customerOrderList;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<CustomerOrder> getOrderItemList() {
		return customerOrderList;
	}

	public void setOrderItemList(List<CustomerOrder> customerOrderList) {
		this.customerOrderList = customerOrderList;
	}
}
