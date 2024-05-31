package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.*;
import com.fdmgroup.courierapp.exception.CustomerNotFoundException;
import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.service.CustomerOrderService;
import com.fdmgroup.courierapp.service.CustomerService;
import com.fdmgroup.courierapp.service.PartyService;
import com.fdmgroup.courierapp.util.CustomerOrderUtil;
import com.fdmgroup.courierapp.util.PartyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    PartyService partyService;
    @Autowired
    PartyUtil partyUtil;

    private final Logger logger = LogManager.getLogger();

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
        		.map(customerOrder -> customerOrderUtil.generateOrderDashboardDetails(customerOrder))
        		.collect(Collectors.toList());
        
        ResponseOrderHistory responseOrderHistory = new ResponseOrderHistory("Success", "Fetch success", orderDetailsList);
        return new ResponseEntity<>(responseOrderHistory, HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ResponseOrderUpdate> updateCustomerOrder(
            @PathVariable("orderId") Long orderId,
            @RequestHeader("username") String username,
            @RequestBody RequestOrderUpdate requestOrderUpdate
    ) {
        CustomerOrder customerOrder;
        Customer customer;
        try {
            customerOrder = customerOrderService.findByCustomerOrderId(orderId);
            customer = customerService.findByUsername(username);
        } catch (OrderNotFoundException | CustomerNotFoundException e) {
            return new ResponseEntity<>(new ResponseOrderUpdate("Failed", e.getMessage()), HttpStatus.OK);
        }

        int lastIndex = customerOrder.getStatus().size() - 1;

        if (!customerOrder.getStatus().get(lastIndex).getStatus().equals(StatusEnum.ORDER_CREATED)) {
            return new ResponseEntity<>(new ResponseOrderUpdate("Failed", "Not allowed to update order anymore"), HttpStatus.OK);
        } else if (!customerOrder.getCustomer().getAccountId().equals(customer.getAccountId())) {
            return new ResponseEntity<>(new ResponseOrderUpdate("Failed", "Order does not belong to Customer Id " + customer.getAccountId()), HttpStatus.OK);
        } else if (!requestOrderUpdate.areAllFieldsPresent()) {
            return new ResponseEntity<>(new ResponseOrderUpdate("Failed", "Fields cannot be empty"), HttpStatus.OK);
        }


        Party updatedSender = partyUtil.mapSender(requestOrderUpdate.getSender(), customerOrder);
        Party updatedRecipient = partyUtil.mapRecipient(requestOrderUpdate.getRecipient(), customerOrder);
        customerOrder.replaceParty(updatedSender, updatedRecipient);
        customerOrder = customerOrderService.saveOrder(customerOrder);

        ResponseOrderUpdate responseOrderUpdate = new ResponseOrderUpdate("Success", "Updated order success", customerOrderUtil.generateOrderDetails(customerOrder));
        return new ResponseEntity<>(responseOrderUpdate, HttpStatus.OK);
    }
}
