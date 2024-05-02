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
public class Sender {

	@Id
	@Column(name = "PK_FK_account_Id")
	private Long accountId;

	@OneToOne
	@JoinColumn(name = "PK_FK_account_Id")
	private Account account;

	private String fullName;
	private String email;
	private String phoneNo;
	private Date lastUpdated;

	@OneToMany(mappedBy = "sender")
	private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

	public Sender(Account account, String fullName, String email, String phoneNo, Date lastUpdated,
			List<OrderItem> orderItemList) {
		super();
		this.account = account;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
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

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

}
