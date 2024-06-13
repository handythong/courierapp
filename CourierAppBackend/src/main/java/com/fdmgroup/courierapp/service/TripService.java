package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.TripNotFoundException;
import com.fdmgroup.courierapp.apimodel.ResponseTrip;
import com.fdmgroup.courierapp.apimodel.TripDetails;
import com.fdmgroup.courierapp.model.RegionEnum;
import com.fdmgroup.courierapp.model.RouteEnum;
import com.fdmgroup.courierapp.model.Trip;
import com.fdmgroup.courierapp.model.TripStatusEnum;
import com.fdmgroup.courierapp.repository.TripRepo;
import com.fdmgroup.courierapp.util.TripFilter;
import com.fdmgroup.courierapp.util.TripSpecification;
import com.fdmgroup.courierapp.util.TripUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.Stream;

@Service
public class TripService {
    @Autowired
    TripRepo tripRepo;

    @Autowired
    TripUtil tripUtil;

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

    public Trip getTripByTripId(long tripId) {
        return tripRepo.findById(tripId);
    }

    public List<Trip> getTripByCourierId(long courierId) {
        return tripRepo.findAllByCourierIdDesc(courierId);
    }

    public List<TripDetails> getTripsByFilter(List<TripFilter> filters) {
        List<Trip> tripList = tripRepo.findAll(TripSpecification.columnEqual(filters));
        List<TripDetails> tripDetailsList = tripList.stream()
                .map(trip -> tripUtil.generateTripDetails(trip))
                .toList();
        return tripDetailsList;
    }

}
