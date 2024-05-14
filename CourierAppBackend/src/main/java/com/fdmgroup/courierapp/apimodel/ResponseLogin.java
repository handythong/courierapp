package com.fdmgroup.courierapp.apimodel;

public class ResponseLogin {
    private String status;
    private String message;
    private String role;
    private String jwt;

    public ResponseLogin() {
    }

    public ResponseLogin(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseLogin(String status, String message, String role,String jwt) {
        this.status = status;
        this.message = message;
        this.role = role;
        this.jwt = jwt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
