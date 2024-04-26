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
public class Courier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courierId_IdSeq")
	@SequenceGenerator(name = "courierId_IdSeq", sequenceName = "courierId_IdSeq", allocationSize = 1, initialValue = 1)
	private long courierId;
	
	private VehicleType vehicle;
	
	@OneToMany(mappedBy = "courier")
	private List<OrderItem> orderItemList = new ArrayList<OrderItem>();

	public Courier(long courierId, VehicleType vehicle, List<OrderItem> orderItemList) {
		super();
		this.courierId = courierId;
		this.vehicle = vehicle;
		this.orderItemList = orderItemList;
	}

	public long getCourierId() {
		return courierId;
	}

	public void setCourierId(long courierId) {
		this.courierId = courierId;
	}

	public VehicleType getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleType vehicle) {
		this.vehicle = vehicle;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
}

enum VehicleType {
	BICYCLE,
	MOTORBIKE,
	CAR,
	SUV,
	VAN,
	LORRY
}
