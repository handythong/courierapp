package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.OrderSender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSenderRepo extends JpaRepository<OrderSender,Long> {
	
}
