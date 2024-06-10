package com.fdmgroup.courierapp.apimodel;

import java.util.List;

public class ResponseCourierList {
    private String status;
    private String message;
    private List<Courier> couriers;

    public ResponseCourierList(String status, String message, List<Courier> couriers) {
        this.status = status;
        this.message = message;
        this.couriers = couriers;
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

    public List<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }
}
