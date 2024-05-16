package com.fdmgroup.courierapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
	
	Optional<Customer> findByEmail(String email);

	@Query(value = "SELECT c.* FROM customer c"
			+ " INNER JOIN account a"
			+ " WHERE c.account_id=a.id"
			+ " AND a.username=?1 ",
			nativeQuery = true)
	Optional<Customer> getCustomerWithAccountUsername(String username);
}
