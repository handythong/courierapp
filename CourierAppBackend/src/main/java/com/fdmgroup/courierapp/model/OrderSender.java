package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

@Entity
public class OrderSender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSenderId_IdSeq")
    @SequenceGenerator(name = "orderSenderId_IdSeq", sequenceName = "orderSenderId_IdSeq", allocationSize = 1, initialValue = 1)
    private long orderSenderId;

    private String senderFullName;
    private String senderEmail;
    private String senderPhoneNo;
    private String pickupAddress;

    @OneToOne(mappedBy = "orderSender")
    private OrderItem orderItem;

    public OrderSender() {
    }

    public OrderSender(long orderSenderId, String senderFullName, String senderEmail, String senderPhoneNo, String pickupAddress, OrderItem orderItem) {
        this.orderSenderId = orderSenderId;
        this.senderFullName = senderFullName;
        this.senderEmail = senderEmail;
        this.senderPhoneNo = senderPhoneNo;
        this.pickupAddress = pickupAddress;
        this.orderItem = orderItem;
    }

    public long getOrderSenderId() {
        return orderSenderId;
    }

    public void setOrderSenderId(long orderSenderId) {
        this.orderSenderId = orderSenderId;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderPhoneNo() {
        return senderPhoneNo;
    }

    public void setSenderPhoneNo(String senderPhoneNo) {
        this.senderPhoneNo = senderPhoneNo;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
}
