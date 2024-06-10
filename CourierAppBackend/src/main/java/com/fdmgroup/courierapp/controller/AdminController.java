package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.ResponseTrip;
import com.fdmgroup.courierapp.apimodel.TripDetails;
import com.fdmgroup.courierapp.model.RegionEnum;
import com.fdmgroup.courierapp.model.RouteEnum;
import com.fdmgroup.courierapp.model.Trip;
import com.fdmgroup.courierapp.model.TripStatusEnum;
import com.fdmgroup.courierapp.service.TripService;
import com.fdmgroup.courierapp.util.TripUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/admin")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    TripService tripService;
    @Autowired
    TripUtil tripUtil;

    @GetMapping("/trips")
    public ResponseEntity<ResponseTrip> getTripsByStatus(
            @RequestParam Optional<String> status,
            @RequestParam Optional<String> route,
            @RequestParam Optional<String> region,
            @RequestParam Optional<Date> date
    ) { List<TripDetails> tripDetailsList = new ArrayList<>();
        if (status.isEmpty() && route.isEmpty() && region.isEmpty() && date.isEmpty()) {
            List<Trip> tripList = tripService.getAllTrips();
            tripDetailsList = tripList.stream()
                    .map(trip -> tripUtil.generateTripDetails(trip))
                    .toList();
        } else {
            List<Trip> tripList = tripService.getTripsWithFilter(status, route, region, date);
            tripDetailsList = tripList.stream()
                    .map(trip -> tripUtil.generateTripDetails(trip))
                    .toList();
        }
        ResponseTrip successResponse = new ResponseTrip("Success", "Fetch success", tripDetailsList);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
}
