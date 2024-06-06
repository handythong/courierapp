package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepo extends JpaRepository<Trip, Long> {
    @Query(value = "SELECT tr.* FROM trip tr"
            + " INNER JOIN courier cr"
            + " ON tr.courier_id = cr.account_id"
            + " WHERE cr.account_id=?1"
            + " ORDER BY tr.trip_date DESC",
            nativeQuery = true)
    List<Trip> findAllByCourierIdDesc(long courierId);

    @Query(value = "SELECT * from Trip",
            nativeQuery = true)
    List<Trip> findAllTrips();
}
