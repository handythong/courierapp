package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.OrderRequest;
import com.fdmgroup.courierapp.apimodel.ResponseRegister;
import com.fdmgroup.courierapp.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orders")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;
    
    @PostMapping("/order")
    public ResponseEntity<ResponseRegister> login(@RequestBody OrderRequest request) {
        System.out.println(request);
        return new ResponseEntity<>(new ResponseRegister("Success", "Created order successfully"), HttpStatus.OK);
    }
}
