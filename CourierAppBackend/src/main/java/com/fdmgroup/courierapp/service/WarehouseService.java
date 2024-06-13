package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.CourierNotFoundException;
import com.fdmgroup.courierapp.exception.WarehouseNotFoundException;
import com.fdmgroup.courierapp.model.Warehouse;
import com.fdmgroup.courierapp.repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepo warehouseRepo;

    public Warehouse saveWarehouse(Warehouse warehouse){
        return warehouseRepo.save(warehouse);
    }

    public Warehouse findById(Long warehouseId) throws WarehouseNotFoundException{
        Optional<Warehouse> optWarehouse = warehouseRepo.findById(warehouseId);
        if (optWarehouse.isPresent()) {
            return optWarehouse.get();
        } else {
            throw new WarehouseNotFoundException("Warehouse not found");
        }
    }

    public Boolean isIdDuplicate(Long warehouseId) {
        Optional<Warehouse> optWarehouse = warehouseRepo.findById(warehouseId);
        return optWarehouse.isPresent();
    }

}
