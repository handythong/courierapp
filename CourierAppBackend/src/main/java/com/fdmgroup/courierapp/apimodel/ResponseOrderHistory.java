package com.fdmgroup.courierapp.apimodel;

import java.util.List;

public class ResponseOrderHistory {
    private String status;
    private String message;
    private List<OrderDashboardDetails> orderHistoryList;

    public ResponseOrderHistory() {
    }

    public ResponseOrderHistory(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseOrderHistory(String status, String message, List<OrderDashboardDetails> orderHistoryList) {
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

    public List<OrderDashboardDetails> getOrderHistoryList() {
        return orderHistoryList;
    }

    public void setOrderHistoryList(List<OrderDashboardDetails> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }
}
