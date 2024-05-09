package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

@Entity
public class OrderRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderRecipientId_IdSeq")
    @SequenceGenerator(name = "orderRecipientId_IdSeq", sequenceName = "orderRecipientId_IdSeq", allocationSize = 1, initialValue = 1)
    private long orderRecipientId;

    private String recipientFullName;
    private String recipientEmail;
    private String recipientPhoneNo;
    private String deliveryAddress;

    @OneToOne(mappedBy = "orderRecipient")
    private OrderItem orderItem;

    public OrderRecipient() {
    }

    public OrderRecipient(long orderRecipientId, String recipientFullName, String recipientEmail, String recipientPhoneNo, String deliveryAddress, OrderItem orderItem) {
        this.orderRecipientId = orderRecipientId;
        this.recipientFullName = recipientFullName;
        this.recipientEmail = recipientEmail;
        this.recipientPhoneNo = recipientPhoneNo;
        this.deliveryAddress = deliveryAddress;
        this.orderItem = orderItem;
    }

    public long getOrderRecipientId() {
        return orderRecipientId;
    }

    public void setOrderRecipientId(long orderRecipientId) {
        this.orderRecipientId = orderRecipientId;
    }

    public String getRecipientFullName() {
        return recipientFullName;
    }

    public void setRecipientFullName(String recipientFullName) {
        this.recipientFullName = recipientFullName;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public String getRecipientPhoneNo() {
        return recipientPhoneNo;
    }

    public void setRecipientPhoneNo(String recipientPhoneNo) {
        this.recipientPhoneNo = recipientPhoneNo;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
}
