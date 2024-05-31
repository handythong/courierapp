package com.fdmgroup.courierapp.apimodel;

import com.fdmgroup.courierapp.model.Address;

public class Recipient {
    private String toFullName;
    private String toEmail;
    private String toPhone;
    private Address toAddress;

    public Recipient() {
    }

    public Recipient(String toFullName, String toEmail, String toPhone, Address toAddress) {
        this.toFullName = toFullName;
        this.toEmail = toEmail;
        this.toPhone = toPhone;
        this.toAddress = toAddress;
    }

    public String getToFullName() {
        return toFullName;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getToPhone() {
        return toPhone;
    }

    public Address getToAddress() {
        return toAddress;
    }

    public void setToFullName(String toFullName) {
        this.toFullName = toFullName;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public void setToPhone(String toPhone) {
        this.toPhone = toPhone;
    }

    public void setToAddress(Address toAddress) {
        this.toAddress = toAddress;
    }
}
