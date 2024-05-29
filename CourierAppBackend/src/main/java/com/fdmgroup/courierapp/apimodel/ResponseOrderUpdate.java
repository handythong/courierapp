package com.fdmgroup.courierapp.apimodel;

public class ResponseOrderUpdate {
    private String status;
    private String message;
    private OrderDetails updatedOrderDetails;

    public ResponseOrderUpdate() {
    }

    public ResponseOrderUpdate(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseOrderUpdate(String status, String message, OrderDetails updatedOrderDetails) {
        this.status = status;
        this.message = message;
        this.updatedOrderDetails = updatedOrderDetails;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public OrderDetails getUpdatedOrderDetails() {
        return updatedOrderDetails;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUpdatedOrderDetails(OrderDetails updatedOrderDetails) {
        this.updatedOrderDetails = updatedOrderDetails;
    }
}
