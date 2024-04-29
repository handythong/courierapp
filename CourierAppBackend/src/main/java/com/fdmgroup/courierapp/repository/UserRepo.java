package com.fdmgroup.courierapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
	
}
