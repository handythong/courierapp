package com.fdmgroup.courierapp.apimodel;

import java.util.List;

public class ResponseOrderHistory {
    private String status;
    private String message;
    private List<OrderDetails> orderHistoryList;

    public ResponseOrderHistory() {
    }

    public ResponseOrderHistory(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseOrderHistory(String status, String message, List<OrderDetails> orderHistoryList) {
        this.status = status;
        this.message = message;
        this.orderHistoryList = orderHistoryList;
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

    public List<OrderDetails> getOrderHistoryList() {
        return orderHistoryList;
    }

    public void setOrderHistoryList(List<OrderDetails> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }
}
