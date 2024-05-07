package com.fdmgroup.courierapp.service;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	private SenderService senderService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final Logger logger = LogManager.getLogger();
	
	public Account registerAccount(Account newAccount) throws Exception {
		if (accountRepo.findByUsername(newAccount.getUsername()) != null ) {
			logger.error("User tried to create with existing username");
			throw new DuplicateAccountException("Username Taken");
		}
		if (!newAccount.getPassword().matches("^(?=.*[a-zA-Z])(?=.*\\d).{8,}$")) {
			logger.error("Password does not contains 8 characters, or at least 1 numbers and 1 alphabet");
			throw new PasswordRuleException("Password need to contains at least 8 characters and have at least alphabet and number");
		}
		newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));
		newAccount.setCreatedOn(new Date());
		newAccount.setLastUpdated(new Date());
		Account createdAccount = accountRepo.save(newAccount);
		return createdAccount;
	}
}
