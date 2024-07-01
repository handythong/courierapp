package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.apimodel.TripDetails;
import com.fdmgroup.courierapp.apimodel.WarehouseDetails;
import com.fdmgroup.courierapp.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

@Component
public class TripUtil {
    public Trip generateDeliveryTrip() {
        Trip trip = new Trip();
        LocalDateTime date = LocalDateTime.now().plusDays(1);

        trip.setRoute(RouteEnum.OUTBOUND);
        trip.setTripDate(date);
        trip.setTripStatus(TripStatusEnum.UNASSIGNED);
        return trip;
    }

    public TripDetails generateTripDetails(Trip trip) {
        TripDetails tripDetails = new TripDetails();
        tripDetails.setTripId(trip.getId());
        tripDetails.setTripDate(trip.getTripDate());
        tripDetails.setTripStatus(trip.getTripStatus().toString());
        tripDetails.setRoute(trip.getRoute().toString());
        tripDetails.setPartyAddress(this.getPartyAddress(trip));
        tripDetails.setSortingWarehouse(this.warehouseDetailsMapper(trip.getWarehouse()));
        tripDetails.setOrderId(trip.getCustomerOrder().getId());
        return tripDetails;
    }

    public TripDetails generateTripDetailsWithCourierId(Trip trip) {
        TripDetails tripDetails = new TripDetails();
        tripDetails.setTripId(trip.getId());
        tripDetails.setTripDate(trip.getTripDate());
        tripDetails.setTripStatus(trip.getTripStatus().toString());
        tripDetails.setRoute(trip.getRoute().toString());
        tripDetails.setPartyAddress(this.getPartyAddress(trip));
        tripDetails.setSortingWarehouse(this.warehouseDetailsMapper(trip.getWarehouse()));
        if (trip.getCourier() == null) {
            tripDetails.setCourierId(Optional.empty());
        } else {
            tripDetails.setCourierId(Optional.of(trip.getCourier().getAccountId()));
        }
        return tripDetails;
    }

    public Address getPartyAddress(Trip trip) {
        CustomerOrder customerOrder = trip.getCustomerOrder();
        if (trip.getRoute() == RouteEnum.INBOUND) {
            Party sender = customerOrder.getParties().stream()
                    .filter(party -> party.getPartyType().equals(PartyEnum.SENDER))
                    .findFirst()
                    .orElse(null);
            return sender.getAddress();
        } else if (trip.getRoute() == RouteEnum.OUTBOUND) {
            Party recipient = customerOrder.getParties().stream()
                    .filter(party -> party.getPartyType().equals(PartyEnum.RECIPIENT))
                    .findFirst()
                    .orElse(null);
            return recipient.getAddress();
        }
        return null;
    }

    public WarehouseDetails warehouseDetailsMapper(Warehouse warehouse) {
        WarehouseDetails warehouseDetails = new WarehouseDetails();
        warehouseDetails.setId(warehouse.getId());
        warehouseDetails.setName(warehouse.getName());
        warehouseDetails.setAddress(warehouse.getAddress());
        warehouseDetails.setPostalCode(warehouse.getPostalCode());
        warehouseDetails.setCity(warehouse.getCity());
        warehouseDetails.setCountry(warehouse.getCountry());
        return warehouseDetails;
    }

//    public RouteEnum routeEnumMapper
}
