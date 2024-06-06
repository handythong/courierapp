package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.RequestTrip;
import com.fdmgroup.courierapp.apimodel.ResponseTrip;
import com.fdmgroup.courierapp.model.Trip;
import com.fdmgroup.courierapp.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/admin")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    TripService tripService;

    @GetMapping("/trips")
    public ResponseEntity<ResponseTrip> getAllTrips() {
        List<Trip> tripList = tripService.getAllTrips();
        ResponseTrip successResponse = new ResponseTrip("Success", "Fetch success", tripList);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

}
