package com.fdmgroup.courierapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.Sender;
import com.fdmgroup.courierapp.repository.SenderRepo;

@Service
public class SenderService {
	
	@Autowired
	SenderRepo senderRepo;
	
}
