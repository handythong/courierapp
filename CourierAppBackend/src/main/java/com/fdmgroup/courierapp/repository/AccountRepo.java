package com.fdmgroup.courierapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
	
}
