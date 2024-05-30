package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.model.Party;
import com.fdmgroup.courierapp.repository.PartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyService {
	
	@Autowired
    PartyRepo partyRepo;

	public Party createParty(Party newParty) {
		return partyRepo.save(newParty);
	}
	
}
