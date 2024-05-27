package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.OrderDashboardDetails;
import com.fdmgroup.courierapp.apimodel.OrderDetails;
import com.fdmgroup.courierapp.apimodel.ResponseOrderHistory;
import com.fdmgroup.courierapp.model.Customer;
import com.fdmgroup.courierapp.model.CustomerOrder;
import com.fdmgroup.courierapp.model.StatusEnum;
import com.fdmgroup.courierapp.service.CustomerOrderService;
import com.fdmgroup.courierapp.service.CustomerService;
import com.fdmgroup.courierapp.util.CustomerOrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/customer")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    CustomerOrderUtil customerOrderUtil;

    @GetMapping("/orders")
    public ResponseEntity<ResponseOrderHistory> getCustomerOrderHistory(@RequestHeader("username") String username){
        Customer customer;
        try {
            customer = customerService.findByUsername(username);
        } catch (Exception e) {
            ResponseOrderHistory responseOrderHistory = new ResponseOrderHistory("Failed", e.getMessage());
            return new ResponseEntity<>(responseOrderHistory, HttpStatus.OK);
        }
        List<CustomerOrder> customerOrders = customerOrderService.getOrderHistoryByCustomerId(customer.getAccountId());
        //List<OrderDetails> orderDetailsList = customerOrders.stream().map(customerOrder -> customerOrderUtil.generateOrderDetails(customerOrder)).collect(Collectors.toList());
        List<OrderDashboardDetails> orderDetailsList = customerOrders.stream()
        		.map(customerOrder -> customerOrderUtil.convertOrderDetails(customerOrder))
        		.collect(Collectors.toList());
        
        ResponseOrderHistory responseOrderHistory = new ResponseOrderHistory("Success", "Fetch success", orderDetailsList);
        return new ResponseEntity<>(responseOrderHistory, HttpStatus.OK);
    }


//    StatusEnum currentStatus = customerOrder.getStatus().stream()
//            .max(Comparator.comparing(status -> status.getStatusUpdateDate()))
//            .get()
//            .getStatus();
}
