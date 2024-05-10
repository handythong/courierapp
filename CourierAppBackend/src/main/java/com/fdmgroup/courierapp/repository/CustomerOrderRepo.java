package com.fdmgroup.courierapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.CustomerOrder;

@Repository
public interface CustomerOrderRepo extends JpaRepository<CustomerOrder,Long> {
	
}
