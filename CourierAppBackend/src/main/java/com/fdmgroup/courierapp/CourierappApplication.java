package com.fdmgroup.courierapp;

import com.fdmgroup.courierapp.model.Account;
import com.fdmgroup.courierapp.model.PaymentSession;
import com.fdmgroup.courierapp.model.Warehouse;
import com.fdmgroup.courierapp.service.AccountService;
import com.fdmgroup.courierapp.service.PaymentThreadingService;
import com.fdmgroup.courierapp.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@SpringBootApplication
public class CourierappApplication {

	@Autowired
	AccountService accountService;
	@Autowired
	WarehouseService warehouseService;
	@Autowired
	PaymentThreadingService paymentThreadingService;

	public static void main(String[] args) {
		SpringApplication.run(CourierappApplication.class, args);
		System.out.println("Application has started on port 8081");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void startup() {
		if
		(!accountService.isDuplicateUsername("Admin001")) {
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
		if (!warehouseService.isIdDuplicate(1L)) {
			Warehouse warehouse = new Warehouse();
			warehouse.setAddress("Church street, Raffles Place");
			warehouse.setCity("Singapore");
			warehouse.setCountry("Singapore");
			warehouse.setPostalCode("012345");
			warehouse.setName("Warehouse 01");
			warehouseService.saveWarehouse(warehouse);
		}

		paymentThreadingService.startTimer();
	}
}
