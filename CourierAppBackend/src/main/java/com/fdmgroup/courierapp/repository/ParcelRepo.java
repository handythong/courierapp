package com.fdmgroup.courierapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.Parcel;

@Repository
public interface ParcelRepo extends JpaRepository<Parcel,Long> {
	
}
