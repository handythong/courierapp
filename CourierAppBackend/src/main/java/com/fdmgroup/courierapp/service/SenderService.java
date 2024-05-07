package com.fdmgroup.courierapp.service;

import java.util.Optional;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.Sender;
import com.fdmgroup.courierapp.repository.SenderRepo;

@Service
public class SenderService {
	
	@Autowired
	private SenderRepo senderRepo;
	
	public Boolean isDuplicateEmail(String email) {
		Optional<Sender> optSender = senderRepo.findByEmail(email);
		return optSender.isPresent();
	}

	public Sender registerSender(Sender newSender) {
		newSender.setLastUpdated(new Date());
		Sender createdSender = senderRepo.save(newSender);
		return createdSender;
	}
}
