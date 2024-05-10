package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.CustomerOrder;
import com.fdmgroup.courierapp.repository.CustomerOrderRepo;

import java.util.Optional;

@Service
public class CustomerOrderService {
	
	@Autowired
	CustomerOrderRepo customerOrderRepo;

	public CustomerOrder findByCustomerOrderId(Long customerOrderId) throws Exception {
		Optional<CustomerOrder> optCustomerOrder = customerOrderRepo.findById(customerOrderId);
		if (optCustomerOrder.isPresent()) {
			return optCustomerOrder.get();
		} else {
			throw new OrderNotFoundException();
		}
	}

	public CustomerOrder createOrder(CustomerOrder customerOrder) {
		return customerOrderRepo.save(customerOrder);
	}
}
