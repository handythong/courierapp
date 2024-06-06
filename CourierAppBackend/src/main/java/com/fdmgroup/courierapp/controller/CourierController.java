package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.*;
import com.fdmgroup.courierapp.exception.CourierNotFoundException;
import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.service.CourierService;
import com.fdmgroup.courierapp.service.CustomerOrderService;
import com.fdmgroup.courierapp.service.StatusService;
import com.fdmgroup.courierapp.service.TripService;
import com.fdmgroup.courierapp.util.CustomerOrderUtil;
import com.fdmgroup.courierapp.util.StatusUtil;
import com.fdmgroup.courierapp.util.TripUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/courier")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CourierController {
    @Autowired
    CourierService courierService;
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    CustomerOrderUtil customerOrderUtil;
    @Autowired
    StatusService statusService;
    @Autowired
    StatusUtil statusUtil;
    @Autowired
    TripUtil tripUtil;
    @Autowired
    TripService tripService;

    @GetMapping("/orders")
    public ResponseEntity<ResponseTripHistory> getCourierTripHistory(@RequestHeader("username") String username){
        Courier courier;
        try {
            courier = courierService.findByUsername(username);
        } catch (Exception e) {
            ResponseTripHistory responseTripHistory = new ResponseTripHistory("Failed", e.getMessage());
            return new ResponseEntity<>(responseTripHistory, HttpStatus.OK);
        }
        List<Trip> courierTrips = tripService.getTripByCourierId(courier.getAccountId());
        List<TripDetails> tripDetailsList = courierTrips.stream()
                .map(trip -> tripUtil.generateTripDetails(trip))
                .collect(Collectors.toList());
//        List<CustomerOrder> customerOrders = customerOrderService.getOrderHistoryByCourierId(courier.getAccountId());
//        List<OrderDetails> orderDetailsList = customerOrders.stream().map(customerOrder -> customerOrderUtil.generateOrderDetails(customerOrder)).collect(Collectors.toList());
//        List<OrderDashboardDetails> orderDetailsList = customerOrders.stream()
//        		.map(customerOrder -> customerOrderUtil.generateOrderDashboardDetails(customerOrder))
//        		.collect(Collectors.toList());
        ResponseTripHistory responseTripHistory = new ResponseTripHistory("Success", "Fetch success", tripDetailsList);
//        ResponseOrderHistory responseOrderHistory = new ResponseOrderHistory("Success", "Fetch success", orderDetailsList);
        return new ResponseEntity<>(responseTripHistory, HttpStatus.OK);
    }
//
//    @PutMapping("/{orderId}")
//    public ResponseEntity<ResponseOrder> updateStatus(@RequestHeader("username") String username, @RequestBody OrderStatus orderStatus, @PathVariable Long orderId) {
//        CustomerOrder customerOrder;
//        Courier courier;
//        try {
//            customerOrder = customerOrderService.findByCustomerOrderId(orderId);
//            courier = courierService.findByUsername(username);
//        } catch (OrderNotFoundException e) {
//            ResponseOrder responseOrder = new ResponseOrder("Failed", "Customer Order Not Found");
//            return new ResponseEntity<>(responseOrder, HttpStatus.OK);
//        } catch (CourierNotFoundException e) {
//            ResponseOrder responseOrder = new ResponseOrder("Failed", "Courier Not Found");
//            return new ResponseEntity<>(responseOrder, HttpStatus.OK);
//        }
//
//        if (courier.getAccountId() != customerOrder.getCourier().getAccountId()) {
//            ResponseOrder responseOrder = new ResponseOrder("Failed", "Courier Not Authorize to update");
//            return new ResponseEntity<>(responseOrder, HttpStatus.OK);
//        }
//        Status status = statusUtil.statusMapper(orderStatus.getStatus());
//
//        StatusEnum[] courierStatusList = {StatusEnum.PICKED_UP, StatusEnum.SORTING, StatusEnum.DELIVERING, StatusEnum.DELIVERED};
//        if (Arrays.asList(courierStatusList).contains(status.getStatus())) {
//            status.setCustomerOrder(customerOrder);
//            status.setRemarks(orderStatus.getRemarks());
//            status.setStatusUpdateDate(new Date());
//            status = statusService.createStatus(status);
//        } else {
//            ResponseOrder responseOrder = new ResponseOrder("Failed", "Updated status is blocked for courier");
//            return new ResponseEntity<>(responseOrder, HttpStatus.OK);
//        }
//
//        customerOrder.appendStatus(status);
//        ResponseOrder responseOrder = new ResponseOrder("Success", "Status Updated", customerOrderUtil.generateOrderDetails(customerOrder));
//        return new ResponseEntity<>(responseOrder, HttpStatus.OK);
//    }
}
