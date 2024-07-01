package com.fdmgroup.courierapp.apimodel;

import java.time.LocalDateTime;

public class OrderStatus {
    private String status;
    private String remarks;
    private LocalDateTime statusUpdateDate;

    public OrderStatus() {
    }

    public OrderStatus(String status, String remarks, LocalDateTime statusUpdateDate) {
        this.status = status;
        this.remarks = remarks;
        this.statusUpdateDate = statusUpdateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getStatusUpdateDate() {
        return statusUpdateDate;
    }

    public void setStatusUpdateDate(LocalDateTime statusUpdateDate) {
        this.statusUpdateDate = statusUpdateDate;
    }
}
