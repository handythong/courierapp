package com.fdmgroup.courierapp.apimodel;

public class Courier {
    private long id;
    private String fullName;
    private float vehicleCapacity;

    public Courier(long id, String fullName, float vehicleCapacity) {
        this.id = id;
        this.fullName = fullName;
        this.vehicleCapacity = vehicleCapacity;
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

    public float getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(float vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }
}
