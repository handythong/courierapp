package com.fdmgroup.courierapp;

import com.fdmgroup.courierapp.model.Account;
import com.fdmgroup.courierapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Date;

@SpringBootApplication
public class CourierappApplication {

	@Autowired
	AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(CourierappApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void createAdminAccountStartup() {
		if (!accountService.isDuplicateUsername("Admin001")) {
			try {
				Account adminAccount = new Account();
				adminAccount.setUsername("Admin001");
				adminAccount.setPassword("Admin001");
				adminAccount.setCreatedOn(new Date());
				adminAccount.setLastUpdated(new Date());
				adminAccount.setRole("ROLE_ADMIN");
				accountService.registerAccount(adminAccount);
			} catch (Exception e) {
				//do nothing
			}
		}
	}
}
