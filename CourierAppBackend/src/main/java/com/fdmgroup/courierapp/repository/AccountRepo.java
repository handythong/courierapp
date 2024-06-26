package com.fdmgroup.courierapp.repository;

import java.util.Optional;

import com.fdmgroup.courierapp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
	
	Optional<Account> findByUsername(String username);

}
