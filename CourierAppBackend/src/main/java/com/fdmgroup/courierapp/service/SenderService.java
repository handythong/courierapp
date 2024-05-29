package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.model.Sender;
import com.fdmgroup.courierapp.repository.SenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SenderService {
	
	@Autowired
	SenderRepo senderRepo;

	public Sender createSender(Sender newSender) {
		return senderRepo.save(newSender);
	}

	public Sender replaceSender(Long outdatedSenderId, Sender updatedSender) {
		senderRepo.deleteById(outdatedSenderId);
		senderRepo.save(updatedSender);
		return updatedSender;
	}
}
