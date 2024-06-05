package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.OrderDetails;
import com.fdmgroup.courierapp.apimodel.RequestOrder;
import com.fdmgroup.courierapp.apimodel.ResponseOrder;
import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.service.*;
import com.fdmgroup.courierapp.util.CustomerOrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerOrderController {
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    StatusService statusService;
    @Autowired
    CustomerOrderUtil customerOrderUtil;
    
    @PostMapping("/orders/create-order")
    public ResponseEntity<ResponseOrder> createOrder(@RequestHeader("username") String username, @RequestBody RequestOrder requestOrder) {

        Parcel newParcel;
        Customer customer;
        try {
            newParcel = customerOrderUtil.generateParcel(requestOrder);
        } catch (Exception e) {
            return new ResponseEntity<ResponseOrder>(new ResponseOrder("Failed", "Parcel detail input is invalid"), HttpStatus.OK);
        }

        List<Party> parties = customerOrderUtil.generateOrderParty(requestOrder);
        try {
            customer = customerService.findByUsername(username);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseOrder("Failed", e.getMessage()), HttpStatus.OK);
        }
        Status orderCreatedStatus = customerOrderUtil.generateOrderCreatedStatus();
        //OrderItem creation
        CustomerOrder newCustomerOrder = new CustomerOrder();
        newCustomerOrder.setOrderDate(new Date());
        newCustomerOrder.setLastUpdated(new Date());
        newCustomerOrder.setDeliveryDate(customerOrderUtil.generateDeliveryDate());
        newCustomerOrder.setParties(parties);
        newCustomerOrder.setParcel(newParcel);
        newCustomerOrder.setCustomer(customer);
        newCustomerOrder = customerOrderService.saveOrder(newCustomerOrder);

        //assign customer order to status and persist it
        orderCreatedStatus.setCustomerOrder(newCustomerOrder);
        orderCreatedStatus = statusService.createStatus(orderCreatedStatus);
        //append generated status to customer order
        newCustomerOrder.appendStatus(orderCreatedStatus);

        OrderDetails orderDetails = customerOrderUtil.generateOrderDetails(newCustomerOrder);
        return new ResponseEntity<>(new ResponseOrder("Success", "Order Created Successfully", orderDetails), HttpStatus.OK);
    }

    @GetMapping("/track/{orderId}")
    public ResponseEntity<ResponseOrder> retrieveOrderId(@PathVariable("orderId") Long orderId) {
        CustomerOrder customerOrder;
        try {
            customerOrder = customerOrderService.findByCustomerOrderId(orderId);
            OrderDetails orderDetails = customerOrderUtil.generateOrderDetails(customerOrder);
            return new ResponseEntity<>(new ResponseOrder("Success", "Order Retrieved Successfully", orderDetails), HttpStatus.OK);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(new ResponseOrder("Failed", e.getMessage(), null), HttpStatus.OK);
        }
    }

}
