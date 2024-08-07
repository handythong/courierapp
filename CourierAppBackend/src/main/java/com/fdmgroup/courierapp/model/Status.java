package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StatusId_IdSeq")
    @SequenceGenerator(name = "StatusId_IdSeq", sequenceName = "StatusId_IdSeq", allocationSize = 1, initialValue = 1)
    private long id;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;
    private String remarks;
    private LocalDateTime statusUpdateDate;

    @ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;

    public Status() {
    }

    public Status(StatusEnum status) {
        this.status = status;
    }

    public Status(StatusEnum status, String remarks, LocalDateTime statusUpdateDate, CustomerOrder customerOrder) {
        this.status = status;
        this.remarks = remarks;
        this.statusUpdateDate = statusUpdateDate;
        this.customerOrder = customerOrder;
    }

    public Status(long id, StatusEnum status, String remarks, LocalDateTime statusUpdateDate, CustomerOrder customerOrder) {
        this.id = id;
        this.status = status;
        this.remarks = remarks;
        this.statusUpdateDate = statusUpdateDate;
        this.customerOrder = customerOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
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

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}

