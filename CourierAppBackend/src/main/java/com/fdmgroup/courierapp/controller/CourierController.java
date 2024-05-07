package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/courier")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CourierController {
    @Autowired
    CourierService courierService;

}
