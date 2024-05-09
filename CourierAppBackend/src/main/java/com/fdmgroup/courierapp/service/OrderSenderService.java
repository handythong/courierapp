package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.model.OrderSender;
import com.fdmgroup.courierapp.model.Parcel;
import com.fdmgroup.courierapp.repository.OrderSenderRepo;
import com.fdmgroup.courierapp.repository.ParcelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSenderService {
	
	@Autowired
	OrderSenderRepo orderSenderRepo;

	public OrderSender createOrderSender(OrderSender newOrderSender) {
		return orderSenderRepo.save(newOrderSender);
	}
	
}
