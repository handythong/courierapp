package com.fdmgroup.courierapp.apimodel;

import com.fdmgroup.courierapp.model.Address;

import java.time.LocalDate;

public class OrderDashboardDetails {
    private long orderId;
    private String toFullName;
    private String fromFullName;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Address toAddress;
    private String currentStatus;

    public OrderDashboardDetails() {
    }

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public String getCurrentStatus() {
		return currentStatus;
	}
	
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getFromFullName() {
		return fromFullName;
	}

	public void setFromFullName(String fromFullName) {
		this.fromFullName = fromFullName;
	}

	public String getToFullName() {
		return toFullName;
	}

	public void setToFullName(String toFullName) {
		this.toFullName = toFullName;
	}

	public Address getToAddress() {
		return toAddress;
	}

	public void setToAddress(Address toAddress) {
		this.toAddress = toAddress;
	}

}
