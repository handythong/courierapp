package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.TripNotFoundException;
import com.fdmgroup.courierapp.apimodel.ResponseTrip;
import com.fdmgroup.courierapp.model.RegionEnum;
import com.fdmgroup.courierapp.model.RouteEnum;
import com.fdmgroup.courierapp.model.Trip;
import com.fdmgroup.courierapp.model.TripStatusEnum;
import com.fdmgroup.courierapp.repository.TripRepo;
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

    public List<Trip> getTripsWithFilter(
            Optional<String> status,
            Optional<String> route,
            Optional<String> region,
            Optional<Date> date
            ) {
        List<Trip> statusTripList = new ArrayList<>();
        List<Trip> routeTripList = new ArrayList<>();
        List<Trip> regionTripList = new ArrayList<>();
        List<Trip> dateTripList = new ArrayList<>();
        if (status.isPresent()) {
            TripStatusEnum[] statusList = TripStatusEnum.values();
            for (TripStatusEnum statusEnum : statusList)
            {
                String statusString = status.get();
                if (statusString.equals(statusEnum.name())) {
                    statusTripList = tripRepo.findTripsByStatus(statusString);
                }
            }
        } else if (route.isPresent()) {
            RouteEnum[] routeList = RouteEnum.values();
            for (RouteEnum routeEnum : routeList)
            {
                String routeString = route.get();
                if (routeString.equals(routeEnum.name())) {
                    routeTripList = tripRepo.findTripsByRoute(routeString);
                }
            }
        } else if (region.isPresent()) {
            RegionEnum[] regionList = RegionEnum.values();
            for (RegionEnum regionEnum : regionList)
            {
                String regionString = region.get();
                if (regionString.equals(regionEnum.name())) {
                    regionTripList = tripRepo.findTripsByRegion(regionString);
                }
            }
        } else if (date.isPresent()) {
            dateTripList = tripRepo.findTripsByDate(date.get());
        }
        List<Trip> tripListWithDuplicates = Stream.of(statusTripList, routeTripList, regionTripList, dateTripList)
                .flatMap(List::stream)
                .toList();
        // Remove duplicates
        List<Trip> tripList = new ArrayList<>(
                new HashSet<>(tripListWithDuplicates));
        return tripList;
    }
}
