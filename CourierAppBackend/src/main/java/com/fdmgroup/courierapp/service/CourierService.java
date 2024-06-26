package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.CourierNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.Courier;
import com.fdmgroup.courierapp.repository.CourierRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CourierService {
	
	@Autowired
	CourierRepo courierRepo;

	public Courier registerCourier(Courier newCourier) {
		newCourier.setLastUpdated(new Date());
		Courier createdCourier = courierRepo.save(newCourier);
		return createdCourier;
	}

	public Courier findByCourierId(long courierId) throws CourierNotFoundException {
		Optional<Courier> optCourier = courierRepo.getCourierByCourierId(courierId);
		if (optCourier.isPresent()) {
			return optCourier.get();
		} else {
			throw new CourierNotFoundException("Courier not found");
		}
	}

	public Courier findByUsername(String username) throws CourierNotFoundException {
		Optional<Courier> optCourier = courierRepo.getCourierWithAccountUsername(username);
		if (optCourier.isPresent()) {
			return optCourier.get();
		} else {
			throw new CourierNotFoundException("Courier not found");
		}
	}

	public List<Courier> getAllCouriers() {
		return courierRepo.getAllCouriers();
	}
}
