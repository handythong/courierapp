package com.fdmgroup.courierapp.apimodel;

public class RequestPayment {
    private String paymentReference;

    public RequestPayment() {
    }

    public RequestPayment(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
}
