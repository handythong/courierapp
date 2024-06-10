package com.fdmgroup.courierapp.apimodel;

public class ResponseTripUpdate {
    private String status;
    private String message;

    private TripDetails tripDetails;

    public ResponseTripUpdate() {
    }

    public ResponseTripUpdate(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseTripUpdate(String status, String message, TripDetails tripDetails) {
        this.status = status;
        this.message = message;
        this.tripDetails = tripDetails;
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

    public TripDetails getTripDetails() {
        return tripDetails;
    }

    public void setTripDetails(TripDetails tripDetails) {
        this.tripDetails = tripDetails;
    }
}
