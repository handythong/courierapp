package com.fdmgroup.courierapp.apimodel;

public class ResponseOrder {
    private String status;
    private String message;

    private OrderDetails orderDetails;
    private String clientSecret;

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

    public ResponseOrder(String status, String message, OrderDetails orderDetails, String clientSecret) {
        this.status = status;
        this.message = message;
        this.orderDetails = orderDetails;
        this.clientSecret = clientSecret;
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

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
