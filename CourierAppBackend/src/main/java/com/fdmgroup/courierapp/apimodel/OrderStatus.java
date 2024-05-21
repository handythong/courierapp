package com.fdmgroup.courierapp.apimodel;

import java.util.Date;

public class OrderStatus {
    private String status;
    private String remarks;
    private Date statusUpdateDate;

    public OrderStatus() {
    }

    public OrderStatus(String status, String remarks, Date statusUpdateDate) {
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

    public Date getStatusUpdateDate() {
        return statusUpdateDate;
    }

    public void setStatusUpdateDate(Date statusUpdateDate) {
        this.statusUpdateDate = statusUpdateDate;
    }
}
