package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

@Entity
public class Recipient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipientId_IdSeq")
    @SequenceGenerator(name = "recipientId_IdSeq", sequenceName = "recipientId_IdSeq", allocationSize = 1, initialValue = 1)
    private long id;

    private String fullName;
    private String email;
    private String phoneNo;
    private String deliveryAddress;

    @OneToOne(mappedBy = "recipient")
    private CustomerOrder customerOrder;

    public Recipient() {
    }

    public Recipient(long id, String fullName, String email, String phoneNo, String deliveryAddress, CustomerOrder customerOrder) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.deliveryAddress = deliveryAddress;
        this.customerOrder = customerOrder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public CustomerOrder getOrderItem() {
        return customerOrder;
    }

    public void setOrderItem(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
