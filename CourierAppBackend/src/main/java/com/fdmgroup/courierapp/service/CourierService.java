package com.fdmgroup.courierapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.Courier;
import com.fdmgroup.courierapp.repository.CourierRepo;

@Service
public class CourierService {
	
	@Autowired
	CourierRepo courierRepo;
	
}
