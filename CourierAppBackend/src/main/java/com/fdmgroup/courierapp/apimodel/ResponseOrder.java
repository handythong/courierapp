package com.fdmgroup.courierapp.apimodel;

public class ResponseOrder {
    private String status;
    private String message;

    private OrderDetails orderDetails;

    public ResponseOrder() {
    }

    public ResponseOrder(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseOrder(String status, String message, OrderDetails orderDetails) {
        this.status = status;
        this.message = message;
        this.orderDetails = orderDetails;
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

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
}
