package com.fdmgroup.courierapp.apimodel;

import com.fdmgroup.courierapp.model.Address;

public class RequestOrder {
    /*
    private String cardNumber;
    private String expiryDate;
    private String nameOnCard;
    private String securityCode;
    */

    //Sender Attributes
    private String fromFullName;
    private String fromEmail;
    private String fromPhone;
    private Address fromAddress;

    //RecipientAttributes
    private String toFullName;
    private String toEmail;
    private String toPhone;
    private Address toAddress;

    //Parcel attributes
    private String weight;
    private String width;
    private String height;
    private String length;
    private String parcelDescription;

    public RequestOrder() { }

    public RequestOrder(Address fromAddress, Address toAddress, String fromFullName, String fromEmail, String fromPhone, String toFullName, String toEmail, String toPhone, String weight, String width, String height, String length, String parcelDescription) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.fromFullName = fromFullName;
        this.fromEmail = fromEmail;
        this.fromPhone = fromPhone;
        this.toFullName = toFullName;
        this.toEmail = toEmail;
        this.toPhone = toPhone;
        this.weight = weight;
        this.width = width;
        this.height = height;
        this.length = length;
        this.parcelDescription = parcelDescription;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    public Address getToAddress() {
        return toAddress;
    }

    public void setToAddress(Address toAddress) {
        this.toAddress = toAddress;
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

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
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

    public String getToPhone() {
        return toPhone;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getParcelDescription() {
        return parcelDescription;
    }

    public void setParcelDescription(String parcelDescription) {
        this.parcelDescription = parcelDescription;
    }
}
