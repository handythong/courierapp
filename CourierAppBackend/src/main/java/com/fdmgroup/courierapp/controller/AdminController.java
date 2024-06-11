package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.RequestCourierAssignment;
import com.fdmgroup.courierapp.apimodel.ResponseTrip;
import com.fdmgroup.courierapp.apimodel.ResponseTripUpdate;
import com.fdmgroup.courierapp.apimodel.TripDetails;
import com.fdmgroup.courierapp.exception.CourierNotFoundException;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.service.CourierService;
import com.fdmgroup.courierapp.service.TripService;
import com.fdmgroup.courierapp.util.TripFilter;
import com.fdmgroup.courierapp.util.TripUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/admin")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    TripService tripService;
    @Autowired
    CourierService courierService;
    @Autowired
    TripUtil tripUtil;

    @PostMapping("/trips")
    public ResponseEntity<ResponseTrip> getTripsByFilter(@RequestBody List<TripFilter> filters) {
        boolean isValidFilter = true;
        for (TripFilter filter : filters) {
            String columnKey = filter.getColumnKey();
            String columnValue = filter.getColumnValue().toString();
            switch (columnKey) {
                case "tripStatus":
                    if (!isColumnValueMatching(columnValue, TripStatusEnum.values())) {
                        isValidFilter = false;
                    }
                    break;
                case "route":
                    if (!isColumnValueMatching(columnValue, RouteEnum.values())){
                        isValidFilter = false;
                    }
                    break;
                case "region":
                    if (!isColumnValueMatching(columnValue, RegionEnum.values())){
                        isValidFilter = false;
                    }
                    break;
                case "tripDate":
                    if (!isDateValid(columnValue)) {
                        isValidFilter = false;
                    }
                    break;
                default:
                    ResponseTrip failedResponse = new ResponseTrip("Failed", "Column key does not match");
                    return new ResponseEntity<>(failedResponse, HttpStatus.OK);
            }
        }
        if (isValidFilter) {
            List<TripDetails> tripDetailsList = tripService.getTripsByFilter(filters);
            ResponseTrip successResponse = new ResponseTrip("Success", "Fetch success", tripDetailsList);
            return new ResponseEntity<>(successResponse, HttpStatus.OK);
        } else {
            ResponseTrip failedResponse = new ResponseTrip("Failed", "Column value does not match");
            return new ResponseEntity<>(failedResponse, HttpStatus.OK);
        }
    }

    private <E extends Enum<E>> boolean isColumnValueMatching(String columnValue, E[] enumValues) {
        for (E enumValue : enumValues) {
            if (columnValue.equals(enumValue.name())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDateValid(String dateStr)
    {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @PutMapping("trips/{tripId}")
    public ResponseEntity<ResponseTripUpdate> assignCourierToTrip(
            @PathVariable("tripId") String tripIdString,
            @RequestBody RequestCourierAssignment requestCourierAssignment
    ) {
        long tripId;
        long assignedCourierId;
        try {
            tripId = Long.parseLong(tripIdString);
            assignedCourierId = Long.parseLong(requestCourierAssignment.getAssignedCourierId());
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new ResponseTripUpdate("Failed", "tripId/assignedCourierId must be numeric."), HttpStatus.OK);
        }

        Trip trip = tripService.getTripByTripId(tripId);

        if (trip.getCourier() != null) {
            String message = "Trip Id " + tripId + " has been assigned to a courier Id " + trip.getCourier().getAccountId();
            return new ResponseEntity<>(new ResponseTripUpdate("Failed", message, tripUtil.generateTripDetails(trip)),  HttpStatus.OK);
        }

        Courier courier;
        try {
            courier = courierService.findByCourierId(assignedCourierId);
        } catch (CourierNotFoundException e) {
            return new ResponseEntity<>(new ResponseTripUpdate("Failed", e.getMessage(), tripUtil.generateTripDetails(trip)), HttpStatus.OK);
        }

        RouteEnum route = trip.getRoute();
        if (route == RouteEnum.INBOUND) {
            trip.getCustomerOrder().appendStatus(new Status(StatusEnum.PROCESSING, "Trip is assigned to courier - Ready for pick up."));
        } else if (route == RouteEnum.OUTBOUND) {
            trip.getCustomerOrder().appendStatus(new Status(StatusEnum.READY_FOR_DELIVERY, "Trip is assigned to courier - Ready for delivery."));
        } else {
            return new ResponseEntity<>(new ResponseTripUpdate("Failed", "Trip's route is invalid. Please contact admin.", tripUtil.generateTripDetails(trip)), HttpStatus.OK);
        }

        trip.setCourier(courier);
        trip.setTripStatus(TripStatusEnum.ASSIGNED);
        tripService.saveTrip(trip);
        return new ResponseEntity<>(new ResponseTripUpdate("Success", "Assign success", tripUtil.generateTripDetails(trip)), HttpStatus.OK);
    }
}
