package com.fdmgroup.courierapp.apimodel;

public class RequestPayment {
    private long amount;
//    private String currency;

    public RequestPayment() {
    }

    public RequestPayment(int amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
}
