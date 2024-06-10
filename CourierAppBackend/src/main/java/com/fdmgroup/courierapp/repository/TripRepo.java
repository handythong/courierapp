package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.util.QueryBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepo extends JpaRepository<Trip, Long> {
    @Query(value = "SELECT tr.* FROM trip tr"
            + " INNER JOIN courier cr"
            + " ON tr.courier_id = cr.account_id"
            + " WHERE cr.account_id=?1"
            + " ORDER BY tr.trip_date DESC",
            nativeQuery = true)
    List<Trip> findAllByCourierIdDesc(long courierId);

    @Query(value = "SELECT * from trip",
            nativeQuery = true)
    List<Trip> findAllTrips();

    @Query(value = "SELECT * from trip tr"
            + " WHERE trip_status=?1"
            + " ORDER BY tr.trip_date DESC",
            nativeQuery = true)
    List<Trip> findTripsByStatus(String status);

    @Query(value = "SELECT * from trip tr"
            + " WHERE route=?1"
            + " ORDER BY tr.trip_date DESC",
            nativeQuery = true)
    List<Trip> findTripsByRoute(String route);

    @Query(value = "SELECT * from Trip tr"
            + " LEFT JOIN CustomerOrder co "
            + " ON tr.customer_order_id = co.id "
            + " LEFT JOIN Party p "
            + " ON co.id = p.customer_order "
            + " LEFT JOIN Address a "
            + " ON p.address_id = a.id "
            + " WHERE a.region=?1 "
            + " ORDER BY tr.trip_date DESC ",
            nativeQuery = true)
    List<Trip> findTripsByRegion(String region);

    @Query(value = "SELECT * from trip tr"
            + " WHERE trip_date LIKE '%?1%' ",
            nativeQuery = true)
    List<Trip> findTripsByDate(Date date);


}
