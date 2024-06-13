package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepo extends JpaRepository<Party,Long> {
	
}
