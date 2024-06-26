package com.fdmgroup.courierapp.repository;

import com.fdmgroup.courierapp.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {

}
