package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.ResponseTrip;
import com.fdmgroup.courierapp.apimodel.TripDetails;
import com.fdmgroup.courierapp.model.RegionEnum;
import com.fdmgroup.courierapp.model.RouteEnum;
import com.fdmgroup.courierapp.model.Trip;
import com.fdmgroup.courierapp.model.TripStatusEnum;
import com.fdmgroup.courierapp.service.TripService;
import com.fdmgroup.courierapp.util.TripFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequestMapping("/admin")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    TripService tripService;

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
}
