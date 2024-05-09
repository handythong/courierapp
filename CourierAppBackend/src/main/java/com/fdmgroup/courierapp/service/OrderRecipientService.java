package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.model.OrderRecipient;
import com.fdmgroup.courierapp.model.OrderSender;
import com.fdmgroup.courierapp.repository.OrderRecipientRepo;
import com.fdmgroup.courierapp.repository.OrderSenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRecipientService {
	
	@Autowired
	OrderRecipientRepo orderRecipientRepo;

	public OrderRecipient createOrderRecipient(OrderRecipient newOrderRecipient) {
		return orderRecipientRepo.save(newOrderRecipient);
	}
	
}
