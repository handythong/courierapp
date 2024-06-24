package com.fdmgroup.courierapp.apimodel;

import com.fdmgroup.courierapp.model.Address;

import java.util.Date;
import java.util.List;

public class OrderDetails {
    private long orderId;
    private List<OrderStatus> orderStatus;
    private Date deliveryDate;
    private Date orderDate;
    //OrderRecipient field
    private Address fromAddress;
    private String fromFullName;
    private String fromEmail;
    private String fromPhoneNo;
    //OrderSender field
    private Address toAddress;
    private String toFullName;
    private String toEmail;
    private String toPhoneNo;
    //Parcel field
    private float weight;
    private float width;
    private float height;
    private float length;
    private String parcelDescription;
    //Payment field
    private String paymentReference;

    public OrderDetails() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public List<OrderStatus> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(List<OrderStatus> orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getFromFullName() {
        return fromFullName;
    }

    public void setFromFullName(String fromFullName) {
        this.fromFullName = fromFullName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromPhoneNo() {
        return fromPhoneNo;
    }

    public void setFromPhoneNo(String fromPhoneNo) {
        this.fromPhoneNo = fromPhoneNo;
    }

    public Address getToAddress() {
        return toAddress;
    }

    public void setToAddress(Address toAddress) {
        this.toAddress = toAddress;
    }

    public String getToFullName() {
        return toFullName;
    }

    public void setToFullName(String toFullName) {
        this.toFullName = toFullName;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getToPhoneNo() {
        return toPhoneNo;
    }

    public void setToPhoneNo(String toPhoneNo) {
        this.toPhoneNo = toPhoneNo;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public String getParcelDescription() {
        return parcelDescription;
    }

    public void setParcelDescription(String parcelDescription) {
        this.parcelDescription = parcelDescription;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }
}
