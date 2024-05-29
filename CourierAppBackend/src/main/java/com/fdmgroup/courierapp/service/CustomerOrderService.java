package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import com.fdmgroup.courierapp.model.Recipient;
import com.fdmgroup.courierapp.model.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.CustomerOrder;
import com.fdmgroup.courierapp.repository.CustomerOrderRepo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {
	
	@Autowired
	CustomerOrderRepo customerOrderRepo;

	public CustomerOrder findByCustomerOrderId(Long customerOrderId) throws OrderNotFoundException {
		Optional<CustomerOrder> optCustomerOrder = customerOrderRepo.findById(customerOrderId);
		if (optCustomerOrder.isPresent()) {
			return optCustomerOrder.get();
		} else {
			throw new OrderNotFoundException("Order Id does not exist");
		}
	}

	public CustomerOrder createOrder(CustomerOrder customerOrder) {
		return customerOrderRepo.save(customerOrder);
	}

	public List<CustomerOrder> getOrderHistoryByCustomerId(Long customerId) {
		return customerOrderRepo.findAllWithCustomerIdDesc(customerId);
	}

	public List<CustomerOrder> getOrderHistoryByCourierId(Long courierId) {
		return customerOrderRepo.findAllWithCourierIdDesc(courierId);
	}

	public CustomerOrder updateCustomerOrder(Long customerOrderId, Sender updatedSender, Recipient updatedRecipient) throws OrderNotFoundException {
		CustomerOrder customerOrder = this.findByCustomerOrderId(customerOrderId);
		customerOrder.setLastUpdated(new Date());
		customerOrder.setSender(updatedSender);
		customerOrder.setRecipient(updatedRecipient);
		customerOrderRepo.save(customerOrder);
		return customerOrder;
	}
}
