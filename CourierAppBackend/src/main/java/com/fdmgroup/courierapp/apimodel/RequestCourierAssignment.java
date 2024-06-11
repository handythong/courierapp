package com.fdmgroup.courierapp.apimodel;

public class RequestCourierAssignment {

    private String assignedCourierId;

    public RequestCourierAssignment() {
    }

    public RequestCourierAssignment(String assignedCourierId) {
        this.assignedCourierId = assignedCourierId;
    }

    public String getAssignedCourierId() {
        return assignedCourierId;
    }

    public void setAssignedCourierId(String assignedCourierId) {
        this.assignedCourierId = assignedCourierId;
    }
}
