package com.fdmgroup.courierapp.apimodel;

import java.util.Date;

public class TripStatus {
    private String tripStatus;
    private String remarks;

    public TripStatus() {
    }

    public TripStatus(String tripStatus, String remarks) {
        this.tripStatus = tripStatus;
        this.remarks = remarks;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
