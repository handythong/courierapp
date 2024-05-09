package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.OrderRecipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRecipientRepo extends JpaRepository<OrderRecipient,Long> {
	
}
