package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.model.Trip;
import com.fdmgroup.courierapp.repository.TripRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {
    @Autowired
    TripRepo tripRepo;


}
