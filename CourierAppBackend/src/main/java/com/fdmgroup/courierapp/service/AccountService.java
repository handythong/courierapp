package com.fdmgroup.courierapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fdmgroup.courierapp.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.Account;
import com.fdmgroup.courierapp.repository.AccountRepo;
import com.fdmgroup.courierapp.exception.DuplicateAccountException;
import com.fdmgroup.courierapp.exception.PasswordRuleException;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Logger logger = LogManager.getLogger();
	
	public Account registerAccount(Account newAccount) throws Exception {
		if (accountRepo.findByUsername(newAccount.getUsername()).isPresent()) {
			logger.error("User tried to create with existing username");
			throw new DuplicateAccountException("Username Taken");
		}
		if (!newAccount.getPassword().matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
			logger.error("Password does not contains 8 characters, or at least 1 numbers, 1 uppercase, and 1 lowercase");
			throw new PasswordRuleException("Password need to contains at least 8 characters and have at least 1 uppercase, 1 lowercase and 1 number");
		}
		newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
		newAccount.setCreatedOn(new Date());
		newAccount.setLastUpdated(new Date());
		Account createdAccount = accountRepo.save(newAccount);
		return createdAccount;
	}
}
