package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.*;
import com.fdmgroup.courierapp.exception.CustomerNotFoundException;
import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.model.Recipient;
import com.fdmgroup.courierapp.model.Sender;
import com.fdmgroup.courierapp.service.CustomerOrderService;
import com.fdmgroup.courierapp.service.CustomerService;
import com.fdmgroup.courierapp.service.RecipientService;
import com.fdmgroup.courierapp.service.SenderService;
import com.fdmgroup.courierapp.util.CustomerOrderUtil;
import com.fdmgroup.courierapp.util.RecipientUtil;
import com.fdmgroup.courierapp.util.SenderUtil;
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
    SenderService senderService;
    @Autowired
    RecipientService recipientService;
    @Autowired
    SenderUtil senderUtil;
    @Autowired
    RecipientUtil recipientUtil;

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

        Sender updatedSender = senderUtil.mapToModel(requestOrderUpdate.getSender(), customerOrder.getSender().getId(), customerOrder);
        Recipient updatedRecipient = recipientUtil.mapToModel(requestOrderUpdate.getRecipient(), customerOrder.getRecipient().getId(), customerOrder);

        try {
            senderService.replaceSender(customerOrder.getSender().getId(), updatedSender);
            recipientService.replaceRecipient(customerOrder.getSender().getId(),updatedRecipient);
            customerOrderService.updateCustomerOrder(customerOrder.getId(), updatedSender, updatedRecipient);
        } catch (OrderNotFoundException e) {
            return new ResponseEntity<>(new ResponseOrderUpdate("Failed", e.getMessage()), HttpStatus.OK);
        }
        ResponseOrderUpdate responseOrderUpdate = new ResponseOrderUpdate("Success", "Updated order success", customerOrderUtil.generateOrderDetails(customerOrder));
        return new ResponseEntity<>(responseOrderUpdate, HttpStatus.OK);
    }
}
