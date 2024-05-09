package com.fdmgroup.courierapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.Courier;
import com.fdmgroup.courierapp.repository.CourierRepo;

import java.util.Date;

@Service
public class CourierService {
	
	@Autowired
	CourierRepo courierRepo;

	public Courier registerCourier(Courier newCourier) {
		newCourier.setLastUpdated(new Date());
		Courier createdCourier = courierRepo.save(newCourier);
		return createdCourier;
	}
}
