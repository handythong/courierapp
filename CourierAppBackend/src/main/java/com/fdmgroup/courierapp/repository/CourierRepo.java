package com.fdmgroup.courierapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fdmgroup.courierapp.model.Courier;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourierRepo extends JpaRepository<Courier,Long> {

    @Query(value = "SELECT cr.* FROM courier cr"
            + " INNER JOIN account a"
            + " WHERE cr.account_id=a.id"
            + " AND a.username=?1 ",
            nativeQuery = true)
    Optional<Courier> getCourierWithAccountUsername(String username);

    @Query(value = "SELECT * FROM courier;", nativeQuery = true)
    List<Courier> getAllCouriers();
}
