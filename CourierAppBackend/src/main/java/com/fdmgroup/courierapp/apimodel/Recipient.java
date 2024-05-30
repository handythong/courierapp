package com.fdmgroup.courierapp.apimodel;

public class Recipient {
    private String toFullName;
    private String toEmail;
    private String toPhone;
    private String toAddress;

    public Recipient() {
    }

    public Recipient(String toFullName, String toEmail, String toPhone, String toAddress) {
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

    public String getToAddress() {
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

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }
}
