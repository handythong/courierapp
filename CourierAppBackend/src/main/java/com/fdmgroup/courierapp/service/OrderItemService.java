package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.OrderItem;
import com.fdmgroup.courierapp.repository.OrderItemRepo;

import java.util.Optional;

@Service
public class OrderItemService {
	
	@Autowired
	OrderItemRepo orderItemRepo;

	public OrderItem findByOrderItemId(Long orderItemId) throws Exception {
		Optional<OrderItem> optOrderItem = orderItemRepo.findById(orderItemId);
		if (optOrderItem.isPresent()) {
			return optOrderItem.get();
		} else {
			throw new OrderNotFoundException();
		}
	}
}
