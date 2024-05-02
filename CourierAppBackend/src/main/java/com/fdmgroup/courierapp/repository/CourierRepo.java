package com.fdmgroup.courierapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.Courier;

@Repository
public interface CourierRepo extends JpaRepository<Courier,Long> {
	
}
