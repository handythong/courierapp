package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

@Entity
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "senderId_IdSeq")
    @SequenceGenerator(name = "senderId_IdSeq", sequenceName = "senderId_IdSeq", allocationSize = 1, initialValue = 1)
    private long id;

    private String fullName;
    private String email;
    private String phoneNo;
    private String pickupAddress;

    @OneToOne(mappedBy = "sender")
    private CustomerOrder customerOrder;

    public Sender() {
    }

    public Sender(long id, String fullName, String email, String phoneNo, String pickupAddress, CustomerOrder customerOrder) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.pickupAddress = pickupAddress;
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

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public CustomerOrder getOrderItem() {
        return customerOrder;
    }

    public void setOrderItem(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
