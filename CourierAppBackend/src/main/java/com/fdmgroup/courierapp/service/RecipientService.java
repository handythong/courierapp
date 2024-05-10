package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.model.Recipient;
import com.fdmgroup.courierapp.repository.RecipientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipientService {
	
	@Autowired
	RecipientRepo recipientRepo;

	public Recipient createRecipient(Recipient newRecipient) {
		return recipientRepo.save(newRecipient);
	}
	
}
