package com.fdmgroup.courierapp.apimodel;

import com.fdmgroup.courierapp.model.Address;

public class Sender {
    private String fromFullName;
    private String fromEmail;
    private String fromPhone;
    private Address fromAddress;

    public Sender() {
    }

    public Sender(String fromFullName, String fromEmail, String fromPhone, Address fromAddress) {
        this.fromFullName = fromFullName;
        this.fromEmail = fromEmail;
        this.fromPhone = fromPhone;
        this.fromAddress = fromAddress;
    }

    public String getFromFullName() {
        return fromFullName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromFullName(String fromFullName) {
        this.fromFullName = fromFullName;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone;
    }

    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }
}
