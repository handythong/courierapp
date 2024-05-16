package com.fdmgroup.courierapp.service;

import java.util.Optional;
import java.util.Date;

import com.fdmgroup.courierapp.exception.CustomerNotFoundException;
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

	public Customer findByEmail(String email) throws CustomerNotFoundException {
		Optional<Customer> optCustomer = customerRepo.findByEmail(email);
		if (optCustomer.isPresent()) {
			return optCustomer.get();
		} else {
			throw new CustomerNotFoundException("Customer not found");
		}
	}

	public Customer findByUsername(String username) throws CustomerNotFoundException {
		Optional<Customer> optCustomer = customerRepo.getCustomerWithAccountUsername(username);
		if (optCustomer.isPresent()) {
			return optCustomer.get();
		} else {
			throw new CustomerNotFoundException("Customer not found");
		}
	}
}
