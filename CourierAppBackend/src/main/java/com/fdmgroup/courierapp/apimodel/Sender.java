package com.fdmgroup.courierapp.apimodel;

public class Sender {
    private String fromFullName;
    private String fromEmail;
    private String fromPhone;
    private String fromAddress;

    public Sender() {
    }

    public Sender(String fromFullName, String fromEmail, String fromPhone, String fromAddress) {
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

    public String getFromAddress() {
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

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
}
