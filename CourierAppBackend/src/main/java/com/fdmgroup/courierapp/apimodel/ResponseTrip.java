package com.fdmgroup.courierapp.apimodel;
import com.fdmgroup.courierapp.model.Trip;

import java.util.List;

public class ResponseTrip {

    private String status;
    private String message;
    private List<TripDetails> trips;

    public ResponseTrip(String status, String message, List<TripDetails> trips) {
        this.status = status;
        this.message = message;
        this.trips = trips;
    }

    public ResponseTrip(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TripDetails> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDetails> trips) { this.trips = trips; }
}
