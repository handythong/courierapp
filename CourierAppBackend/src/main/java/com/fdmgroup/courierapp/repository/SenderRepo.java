package com.fdmgroup.courierapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.Sender;

@Repository
public interface SenderRepo extends JpaRepository<Sender,Long> {
	
}
