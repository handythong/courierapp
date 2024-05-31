package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.PartyNotFoundException;
import com.fdmgroup.courierapp.model.Party;
import com.fdmgroup.courierapp.repository.PartyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartyService {
	
	@Autowired
    PartyRepo partyRepo;

	public Party saveParty(Party newParty) {
		return partyRepo.save(newParty);
	}

	public Party findById(Long partyId) throws PartyNotFoundException{
		Optional<Party> optParty = partyRepo.findById(partyId);
		if (optParty.isPresent()) {
			return optParty.get();
		} else {
			throw new PartyNotFoundException("Party not found");
		}
	}

}
