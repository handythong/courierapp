package com.fdmgroup.courierapp.apimodel;

import com.stripe.model.PaymentIntent;

public class ResponsePayment {
    private String status;
    private String message;
    private String clientSecret;

    public ResponsePayment() {
    }

    public ResponsePayment(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponsePayment(String status, String message, String clientSecret) {
        this.status = status;
        this.message = message;
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

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
