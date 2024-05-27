package com.fdmgroup.courierapp.service;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.Account;
import com.fdmgroup.courierapp.repository.AccountRepo;
import com.fdmgroup.courierapp.exception.PasswordRuleException;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Logger logger = LogManager.getLogger();
	
	public Account registerAccount(Account newAccount) throws Exception {
		if (!newAccount.getPassword().matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
			logger.error("Password does not contains 8 characters, or at least 1 numbers, 1 uppercase, and 1 lowercase");
			throw new PasswordRuleException("Password need to contains at least 8 characters and have at least 1 uppercase, 1 lowercase and 1 number");
		}
		newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
		newAccount.setCreatedOn(new Date());
		newAccount.setLastUpdated(new Date());
        return accountRepo.save(newAccount);
	}

	public Boolean isDuplicateUsername(String username) {
		Optional<Account> optAccount = accountRepo.findByUsername(username);
		return optAccount.isPresent();
	}
}
