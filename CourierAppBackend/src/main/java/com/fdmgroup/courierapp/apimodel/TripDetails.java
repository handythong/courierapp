package com.fdmgroup.courierapp.apimodel;

import com.fdmgroup.courierapp.model.Address;
import com.fdmgroup.courierapp.model.Warehouse;

import java.util.Date;
import java.util.Optional;

public class TripDetails {
    private long tripId;
    private Date tripDate;
    private String tripStatus;
    private String route;
    private Address partyAddress;
    private WarehouseDetails sortingWarehouse;
    private Optional<Long> courierId;

    public TripDetails() {
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Address getPartyAddress() {
        return partyAddress;
    }

    public void setPartyAddress(Address partyAddress) {
        this.partyAddress = partyAddress;
    }

    public WarehouseDetails getSortingWarehouse() {
        return sortingWarehouse;
    }

    public void setSortingWarehouse(WarehouseDetails sortingWarehouse) {
        this.sortingWarehouse = sortingWarehouse;
    }

    public Optional<Long> getCourierId() {
        return courierId;
    }

    public void setCourierId(Optional<Long> courierId) {
        this.courierId = courierId;
    }
}
