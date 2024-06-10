package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.TripNotFoundException;
import com.fdmgroup.courierapp.model.Trip;
import com.fdmgroup.courierapp.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    @Autowired
    TripRepo tripRepo;

    public Trip saveTrip(Trip trip){
        return tripRepo.save(trip);
    }
    public Trip findById(Long tripId) throws TripNotFoundException {
        Optional<Trip> optTrip = tripRepo.findById(tripId);
        if (optTrip.isPresent()) {
            return optTrip.get();
        } else {
            throw new TripNotFoundException("Trip not found");
        }
    }

    public List<Trip> getTripByCourierId(long courierId) {
        return tripRepo.findAllByCourierIdDesc(courierId);
    }
    public List<Trip> getAllTrips() {
        return tripRepo.findAllTrips();
    }
}
