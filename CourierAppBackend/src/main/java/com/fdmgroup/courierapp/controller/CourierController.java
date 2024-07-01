package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.*;
import com.fdmgroup.courierapp.exception.CourierNotFoundException;
import com.fdmgroup.courierapp.exception.TripNotFoundException;
import com.fdmgroup.courierapp.exception.WarehouseNotFoundException;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.service.*;
import com.fdmgroup.courierapp.model.Courier;
import com.fdmgroup.courierapp.service.CourierService;
import com.fdmgroup.courierapp.service.StatusService;
import com.fdmgroup.courierapp.service.TripService;
import com.fdmgroup.courierapp.util.StatusUtil;
import com.fdmgroup.courierapp.util.TripUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/courier")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CourierController {
    @Autowired
    CourierService courierService;
    @Autowired
    StatusService statusService;
    @Autowired
    StatusUtil statusUtil;
    @Autowired
    TripUtil tripUtil;
    @Autowired
    TripService tripService;
    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/trips")
    public ResponseEntity<ResponseTripHistory> getCourierTripHistory(@RequestHeader("username") String username){
        Courier courier;
        try {
            courier = courierService.findByUsername(username);
        } catch (Exception e) {
            ResponseTripHistory responseTripHistory = new ResponseTripHistory("Failed", e.getMessage());
            return new ResponseEntity<>(responseTripHistory, HttpStatus.OK);
        }
        List<Trip> courierTrips = tripService.getTripByCourierId(courier.getAccountId());
        List<TripDetails> tripDetailsList = courierTrips.stream()
                .map(trip -> tripUtil.generateTripDetails(trip))
                .collect(Collectors.toList());
        ResponseTripHistory responseTripHistory = new ResponseTripHistory("Success", "Fetch success", tripDetailsList);
        return new ResponseEntity<>(responseTripHistory, HttpStatus.OK);
    }

    @PutMapping("/{tripId}")
    public ResponseEntity<ResponseTripUpdate> updateStatus(@RequestHeader("username") String username, @RequestBody TripStatus tripStatus, @PathVariable Long tripId) {
        Trip trip;
        Courier courier;
        try {
            trip = tripService.findById(tripId);
            courier = courierService.findByUsername(username);
        } catch (TripNotFoundException e) {
            ResponseTripUpdate responseTripUpdate = new ResponseTripUpdate("Failed", "Trip Not Found");
            return new ResponseEntity<>(responseTripUpdate, HttpStatus.OK);
        } catch (CourierNotFoundException e) {
            ResponseTripUpdate responseTripUpdate = new ResponseTripUpdate("Failed", "Courier Not Found");
            return new ResponseEntity<>(responseTripUpdate, HttpStatus.OK);
        }

        if (courier.getAccountId() != trip.getCourier().getAccountId()) {
            ResponseTripUpdate responseTripUpdate = new ResponseTripUpdate("Failed", "Courier Not Authorize to update");
            return new ResponseEntity<>(responseTripUpdate, HttpStatus.OK);
        }
        TripStatusEnum tripStatusEnum = statusUtil.tripStatusMapper(tripStatus.getTripStatus());

        Status status;
        if (trip.getRoute() == RouteEnum.INBOUND) {
            switch(tripStatusEnum) {
                case RETRIEVED:
                    status = new Status(StatusEnum.PICKED_UP);
                    break;
                case COMPLETED:
                    status = new Status(StatusEnum.SORTING);
                    //generate delivery trip when pickup trip is completed
                    Trip deliveryTrip = tripUtil.generateDeliveryTrip();
                    try {
                        deliveryTrip.setWarehouse(warehouseService.findById(1L));
                    } catch (WarehouseNotFoundException e) {
                        ResponseTripUpdate responseTripUpdate = new ResponseTripUpdate("Failed", e.getMessage());
                        return new ResponseEntity<>(responseTripUpdate, HttpStatus.OK);
                    }
                    deliveryTrip.setCustomerOrder(trip.getCustomerOrder());
                    tripService.saveTrip(deliveryTrip);
                    break;
                default:
                    ResponseTripUpdate responseTripUpdate = new ResponseTripUpdate("Failed", "Status unauthorized to update");
                    return new ResponseEntity<>(responseTripUpdate, HttpStatus.OK);
            }
        } else {
            switch(tripStatusEnum) {
                case RETRIEVED:
                    status = new Status(StatusEnum.DELIVERING);
                    break;
                case COMPLETED:
                    status = new Status(StatusEnum.DELIVERED);
                    break;
                default:
                    ResponseTripUpdate responseTripUpdate = new ResponseTripUpdate("Failed", "Status unauthorized to update");
                    return new ResponseEntity<>(responseTripUpdate, HttpStatus.OK);
            }
        }
        trip.setTripStatus(tripStatusEnum);
        status.setCustomerOrder(trip.getCustomerOrder());
        status.setRemarks(tripStatus.getRemarks());
        status.setStatusUpdateDate(LocalDateTime.now());
        statusService.createStatus(status);
        tripService.saveTrip(trip);

        ResponseTripUpdate responseTripUpdate = new ResponseTripUpdate("Success", "Status Updated", tripUtil.generateTripDetails(trip));
        return new ResponseEntity<>(responseTripUpdate, HttpStatus.OK);
    }

    @GetMapping("/couriers")
    public ResponseEntity<ResponseCourierList> getCourierList() {
        List<com.fdmgroup.courierapp.apimodel.Courier> couriers = courierService.getAllCouriers()
                .stream()
                .map(courier -> new com.fdmgroup.courierapp.apimodel.Courier(courier.getAccountId(), courier.getFullName(), courier.getVehicleCapacity()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ResponseCourierList("Success", "Retrieved successfully", couriers), HttpStatus.OK);
    }

}
