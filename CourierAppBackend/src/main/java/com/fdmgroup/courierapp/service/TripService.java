package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.model.Trip;
import com.fdmgroup.courierapp.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    @Autowired
    TripRepo tripRepo;

    public Trip saveTrip(Trip trip){
        return tripRepo.save(trip);
    }

    public List<Trip> getTripByCourierId(long courierId) {
        return tripRepo.findAllByCourierIdDesc(courierId);
    }
    public List<Trip> getAllTrips() {
        return tripRepo.findAllTrips();
    }
}
