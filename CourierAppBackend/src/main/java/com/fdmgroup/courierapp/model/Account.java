package com.fdmgroup.courierapp.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountId_IdSeq")
	@SequenceGenerator(name = "accountId_IdSeq", sequenceName = "accountId_IdSeq", allocationSize = 1, initialValue = 1)
	private long id;
	
	private String username;
	private String password;
	private String role;
	private Date lastUpdated;
	private Date createdOn;

	public Account() {
	}

	public Account(long id, String username, String password, String role,
				   Date lastUpdated, Date createdOn) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.lastUpdated = lastUpdated;
		this.createdOn = createdOn;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	
}
