package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.Sender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenderRepo extends JpaRepository<Sender,Long> {
	
}
