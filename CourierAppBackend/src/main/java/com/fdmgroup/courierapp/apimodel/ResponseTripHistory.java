package com.fdmgroup.courierapp.apimodel;

import java.util.List;

public class ResponseTripHistory {
    private String status;
    private String message;
    private List<TripDetails> tripDetailsList;

    public ResponseTripHistory() {
    }

    public ResponseTripHistory(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseTripHistory(String status, String message, List<TripDetails> tripDetailsList) {
        this.status = status;
        this.message = message;
        this.tripDetailsList = tripDetailsList;
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

    public List<TripDetails> getTripDetailsList() {
        return tripDetailsList;
    }

    public void setTripDetailsList(List<TripDetails> tripDetailsList) {
        this.tripDetailsList = tripDetailsList;
    }
}
