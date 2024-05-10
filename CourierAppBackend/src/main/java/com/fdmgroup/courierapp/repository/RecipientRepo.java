package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipientRepo extends JpaRepository<Recipient,Long> {
	
}
