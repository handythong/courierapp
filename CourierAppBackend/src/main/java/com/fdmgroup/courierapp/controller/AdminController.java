package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.*;
import com.fdmgroup.courierapp.exception.CourierNotFoundException;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.model.Courier;
import com.fdmgroup.courierapp.service.CourierService;
import com.fdmgroup.courierapp.service.TripService;
import com.fdmgroup.courierapp.util.CustomerOrderUtil;
import com.fdmgroup.courierapp.util.TripFilter;
import com.fdmgroup.courierapp.util.TripUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    @Autowired
    CustomerOrderUtil customerOrderUtil;

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

    @PutMapping("trips/assign/{tripId}")
    public ResponseEntity<ResponseCourierAssignment> assignCourierToTrip(
            @PathVariable("tripId") String tripIdString,
            @RequestBody RequestCourierAssignment requestCourierAssignment
    ) {
        long tripId;
        long assignedCourierId;
        try {
            tripId = Long.parseLong(tripIdString);
            assignedCourierId = Long.parseLong(requestCourierAssignment.getAssignedCourierId());
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new ResponseCourierAssignment("Failed", "tripId/assignedCourierId must be numeric."), HttpStatus.OK);
        }

        Trip trip = tripService.getTripByTripId(tripId);

        if (trip.getCourier() != null) {
            String message = "Trip Id " + tripId + " has been assigned to a courier Id " + trip.getCourier().getAccountId();
            ResponseCourierAssignment responseCourierAssignment = new ResponseCourierAssignment(
                    "Failed",
                    message,
                    tripUtil.generateTripDetailsWithCourierId(trip),
                    customerOrderUtil.mappedOrderStatus(trip.getCustomerOrder())
            );
            return new ResponseEntity<>(responseCourierAssignment,  HttpStatus.OK);
        }

        Courier courier;
        try {
            courier = courierService.findByCourierId(assignedCourierId);
        } catch (Exception e) {
            ResponseCourierAssignment responseCourierAssignment = new ResponseCourierAssignment(
                    "Failed",
                    e.getMessage(),
                    tripUtil.generateTripDetailsWithCourierId(trip),
                    customerOrderUtil.mappedOrderStatus(trip.getCustomerOrder())
            );
            return new ResponseEntity<>(responseCourierAssignment, HttpStatus.OK);
        }

        RouteEnum route = trip.getRoute();
        Status status;
        if (route == RouteEnum.INBOUND) {
            status = new Status(
                    StatusEnum.PROCESSING,
                    "Trip is assigned to courier - Ready for pick up.",
                    LocalDateTime.now(),
                    trip.getCustomerOrder());
        } else if (route == RouteEnum.OUTBOUND) {
            status = new Status(
                    StatusEnum.READY_FOR_DELIVERY,
                    "Trip is assigned to courier - Ready for delivery.",
                    LocalDateTime.now(),
                    trip.getCustomerOrder()
            );
        } else {
            ResponseCourierAssignment responseCourierAssignment = new ResponseCourierAssignment(
                    "Failed",
                    "Trip's route is invalid. Please contact admin.",
                    tripUtil.generateTripDetailsWithCourierId(trip),
                    customerOrderUtil.mappedOrderStatus(trip.getCustomerOrder())
            );
            return new ResponseEntity<>(responseCourierAssignment, HttpStatus.OK);
        }
        trip.getCustomerOrder().appendStatus(status);

        trip.setCourier(courier);
        trip.setTripStatus(TripStatusEnum.ASSIGNED);
        tripService.saveTrip(trip);
        ResponseCourierAssignment responseCourierAssignment = new ResponseCourierAssignment(
                "Success",
                "Assign success",
                tripUtil.generateTripDetailsWithCourierId(trip),
                customerOrderUtil.mappedOrderStatus(trip.getCustomerOrder())
        );
        return new ResponseEntity<>(responseCourierAssignment, HttpStatus.OK);
    }

    @PutMapping("trips/unassign/{tripId}")
    public ResponseEntity<ResponseCourierAssignment> unassignCourierToTrip(@PathVariable("tripId") String tripIdString) {
        long tripId;
        try {
            tripId = Long.parseLong(tripIdString);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new ResponseCourierAssignment("Failed", "tripId must be numeric."), HttpStatus.OK);
        }

        Trip trip = tripService.getTripByTripId(tripId);

        if (trip.getCourier() == null) {
            System.out.println(trip.getCourier());
            ResponseCourierAssignment responseCourierAssignment = new ResponseCourierAssignment(
                    "Failed",
                    "Trip Id " + tripId + " has not been assigned a courier yet. Unable to unassign.",
                    tripUtil.generateTripDetailsWithCourierId(trip),
                    customerOrderUtil.mappedOrderStatus(trip.getCustomerOrder())
            );
            return new ResponseEntity<>(responseCourierAssignment,  HttpStatus.OK);
        }

        RouteEnum route = trip.getRoute();
        Status status;
        if (route == RouteEnum.INBOUND) {
            status = new Status(
                    StatusEnum.ORDER_CREATED,
                    "Courier is unassigned",
                    LocalDateTime.now(),
                    trip.getCustomerOrder()
            );
        } else if (route == RouteEnum.OUTBOUND) {
            status = new Status(
                    StatusEnum.SORTING,
                    "Courier is unassigned",
                    LocalDateTime.now(),
                    trip.getCustomerOrder()
            );
        } else {
            ResponseCourierAssignment responseCourierAssignment = new ResponseCourierAssignment(
                    "Failed",
                    "Trip's route is invalid. Please contact admin.",
                    tripUtil.generateTripDetails(trip),
                    customerOrderUtil.mappedOrderStatus(trip.getCustomerOrder())
            );
            return new ResponseEntity<>(responseCourierAssignment, HttpStatus.OK);
        }
        trip.getCustomerOrder().appendStatus(status);

        trip.unassignCourier();
        trip.setTripStatus(TripStatusEnum.UNASSIGNED);
        tripService.saveTrip(trip);
        ResponseCourierAssignment responseCourierAssignment = new ResponseCourierAssignment(
                "Success",
                "Unassign success",
                tripUtil.generateTripDetails(trip),
                customerOrderUtil.mappedOrderStatus(trip.getCustomerOrder())
        );
        return new ResponseEntity<>(responseCourierAssignment, HttpStatus.OK);
    }

    private <E extends Enum<E>> boolean isColumnValueMatching(String columnValue, E[] enumValues) {
        for (E enumValue : enumValues) {
            if (columnValue.equals(enumValue.name())) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDateValid(String dateStr) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
