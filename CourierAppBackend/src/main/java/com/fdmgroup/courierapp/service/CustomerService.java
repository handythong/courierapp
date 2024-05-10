package com.fdmgroup.courierapp.service;

import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.Customer;
import com.fdmgroup.courierapp.repository.CustomerRepo;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	public Boolean isDuplicateEmail(String email) {
		Optional<Customer> optCustomer = customerRepo.findByEmail(email);
		return optCustomer.isPresent();
	}

	public Customer registerCustomer(Customer newCustomer) {
		newCustomer.setLastUpdated(new Date());
		Customer createdCustomer = customerRepo.save(newCustomer);
		return createdCustomer;
	}

	public Customer findByEmail(String email) {
		return customerRepo.findByEmail(email).get();
	}
}
