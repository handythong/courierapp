package com.fdmgroup.courierapp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userId_IdSeq")
	@SequenceGenerator(name = "userId_IdSeq", sequenceName = "userId_IdSeq", allocationSize = 1, initialValue = 1)
	private long userId;
	
	private String name;
	private String email;
	private String phone;
	private String address;
	
	@OneToMany(mappedBy="sender")
	private List<OrderItem> sendingOrderList = new ArrayList<OrderItem>();
	
	@OneToMany(mappedBy="recipient")
	private List<OrderItem> receiveOrderList = new ArrayList<OrderItem>();

	public User(long userId, String name, String email, String phone, String address, List<OrderItem> sendingOrderList,
			List<OrderItem> receiveOrderList) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.sendingOrderList = sendingOrderList;
		this.receiveOrderList = receiveOrderList;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<OrderItem> getSendingOrderList() {
		return sendingOrderList;
	}

	public void setSendingOrderList(List<OrderItem> sendingOrderList) {
		this.sendingOrderList = sendingOrderList;
	}

	public List<OrderItem> getReceiveOrderList() {
		return receiveOrderList;
	}

	public void setReceiveOrderList(List<OrderItem> receiveOrderList) {
		this.receiveOrderList = receiveOrderList;
	}
}
