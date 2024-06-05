package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepo extends JpaRepository<Trip, Long> {

}
