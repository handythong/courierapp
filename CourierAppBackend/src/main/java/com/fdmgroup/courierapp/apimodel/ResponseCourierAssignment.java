package com.fdmgroup.courierapp.apimodel;

import java.util.List;

public class ResponseCourierAssignment {
    private String status;
    private String message;

    private TripDetails tripDetails;
    private List<OrderStatus> orderStatusList;

    public ResponseCourierAssignment() {
    }

    public ResponseCourierAssignment(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseCourierAssignment(String status, String message, TripDetails tripDetails, List<OrderStatus> orderStatusList) {
        this.status = status;
        this.message = message;
        this.tripDetails = tripDetails;
        this.orderStatusList = orderStatusList;
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

    public List<OrderStatus> getOrderStatusList() {
        return orderStatusList;
    }

    public void setOrderStatusList(List<OrderStatus> orderStatusList) {
        this.orderStatusList = orderStatusList;
    }
}
