package com.fdmgroup.courierapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.OrderItem;
import com.fdmgroup.courierapp.repository.OrderItemRepo;

@Service
public class OrderItemService {
	
	@Autowired
	OrderItemRepo orderItemRepo;
	
}
