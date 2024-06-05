package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.model.Warehouse;
import com.fdmgroup.courierapp.repository.WarehouseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepo warehouseRepo;


}
