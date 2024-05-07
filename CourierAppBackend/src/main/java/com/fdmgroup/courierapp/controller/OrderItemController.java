package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orders")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

}
