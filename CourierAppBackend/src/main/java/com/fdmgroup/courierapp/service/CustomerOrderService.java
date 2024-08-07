package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.CustomerOrder;
import com.fdmgroup.courierapp.repository.CustomerOrderRepo;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {
	
	@Autowired
	CustomerOrderRepo customerOrderRepo;

	private final Logger logger = LogManager.getLogger();

	public CustomerOrder findByCustomerOrderId(Long customerOrderId) throws OrderNotFoundException {
		Optional<CustomerOrder> optCustomerOrder = customerOrderRepo.findById(customerOrderId);
		if (optCustomerOrder.isPresent()) {
			return optCustomerOrder.get();
		} else {
			throw new OrderNotFoundException("Order not found in database");
		}
	}

	public CustomerOrder findByPaymentReference(String paymentReference) throws OrderNotFoundException {
		Optional<CustomerOrder> optCustomerOrder = customerOrderRepo.findByPaymentReference(paymentReference);
		if (optCustomerOrder.isPresent()) {
			return optCustomerOrder.get();
		} else {
			throw new OrderNotFoundException("Order not found in database");
		}
	}

	public CustomerOrder saveOrder(CustomerOrder customerOrder) {
		return customerOrderRepo.save(customerOrder);
	}

	public List<CustomerOrder> getOrderHistoryByCustomerId(Long customerId) {
		return customerOrderRepo.findAllWithCustomerIdDesc(customerId);
	}

	public List<CustomerOrder> getOrderHistoryByCourierId(Long courierId) {
		return customerOrderRepo.findAllWithCourierIdDesc(courierId);
	}

	public List<CustomerOrder> getAllOrder() {
		return customerOrderRepo.findAll();
	}
}
