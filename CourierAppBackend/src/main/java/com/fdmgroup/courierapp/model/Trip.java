package com.fdmgroup.courierapp.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tripId_IdSeq")
    @SequenceGenerator(name = "tripId_IdSeq", sequenceName = "tripId_IdSeq", allocationSize = 1, initialValue = 1)
    private long id;

    private Date tripDate;
    @Enumerated(EnumType.STRING)
    private RouteEnum route;
    @Enumerated(EnumType.STRING)
    private TripStatusEnum tripStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courier_id")
    private Courier courier;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public Trip() {
    }

    public Trip(long id, Date tripDate, RouteEnum route, TripStatusEnum tripStatus, CustomerOrder customerOrder, Courier courier, Warehouse warehouse) {
        this.id = id;
        this.tripDate = tripDate;
        this.route = route;
        this.tripStatus = tripStatus;
        this.customerOrder = customerOrder;
        this.courier = courier;
        this.warehouse = warehouse;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public RouteEnum getRoute() {
        return route;
    }

    public void setRoute(RouteEnum route) {
        this.route = route;
    }

    public TripStatusEnum getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatusEnum tripStatus) {
        this.tripStatus = tripStatus;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public void unassignCourier() { this.courier = null; }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
