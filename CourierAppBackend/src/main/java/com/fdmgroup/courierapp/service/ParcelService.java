package com.fdmgroup.courierapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.courierapp.model.Parcel;
import com.fdmgroup.courierapp.repository.ParcelRepo;

@Service
public class ParcelService {
	
	@Autowired
	ParcelRepo parcelRepo;

	public Parcel createParcel(Parcel newParcel) {
		return parcelRepo.save(newParcel);
	}
	
}
