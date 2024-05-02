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
	private long accountId;
	
	private String username;
	private String password;
	private String accountType;
	private Date lastUpdated;
	private Date createdOn;
	
	public Account(long accountId, String username, String password, String accountType, Date lastUpdated,
			Date createdOn) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.lastUpdated = lastUpdated;
		this.createdOn = createdOn;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
