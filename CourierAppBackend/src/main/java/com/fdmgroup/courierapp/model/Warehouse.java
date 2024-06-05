package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouseId_IdSeq")
    @SequenceGenerator(name = "warehouseId_IdSeq", sequenceName = "warehouseId_IdSeq", allocationSize = 1, initialValue = 1)
    private long id;

    private String name;
    private String address;
    private String postalCode;
    private String country;
    private String city;

    @OneToMany(mappedBy = "warehouse")
    private List<Trip> trips = new ArrayList<>();

    public Warehouse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
