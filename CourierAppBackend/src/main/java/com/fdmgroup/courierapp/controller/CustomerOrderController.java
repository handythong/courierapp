package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.*;
import com.fdmgroup.courierapp.exception.CustomerNotFoundException;
import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import com.fdmgroup.courierapp.exception.WarehouseNotFoundException;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.service.*;
import com.fdmgroup.courierapp.util.CustomerOrderUtil;
import com.fdmgroup.courierapp.util.StatusUtil;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerOrderController {
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    WarehouseService warehouseService;
    @Autowired
    CustomerOrderUtil customerOrderUtil;
    @Autowired
    PaymentService paymentService;
    @Autowired
    StatusUtil statusUtil;
    
    @PostMapping("/orders/create-order")
    public ResponseEntity<ResponseOrder> createOrder(@RequestHeader("username") String username, @RequestBody RequestOrder requestOrder) {
        try {
            PaymentIntent paymentIntent = paymentService.generateStripePaymentIntent(requestOrder.getPrice());
            String clientSecret = paymentIntent.getClientSecret();
            String paymentReference = paymentIntent.getId();
            Parcel newParcel = customerOrderUtil.generateParcel(requestOrder);
            Party recipient = customerOrderUtil.generateOrderRecipient(requestOrder);
            Party sender = customerOrderUtil.generateOrderSender(requestOrder);
            Customer customer = customerService.findByUsername(username);
            Status orderCreatedStatus = statusUtil.generateAwaitPaymentStatus();

            //OrderItem creation
            CustomerOrder newCustomerOrder = new CustomerOrder();
            newCustomerOrder.setOrderDate(LocalDateTime.now());
            newCustomerOrder.setLastUpdated(LocalDateTime.now());
            newCustomerOrder.setDeliveryDate(customerOrderUtil.generateDeliveryDate());
            newCustomerOrder.setParcel(newParcel);
            newCustomerOrder.setCustomer(customer);
            newCustomerOrder.setPaymentReference(paymentReference);

            orderCreatedStatus.setCustomerOrder(newCustomerOrder);
            newCustomerOrder.getStatuses().add(orderCreatedStatus);

            recipient.setCustomerOrder(newCustomerOrder);
            recipient.getAddress().setRegion(recipient.getAddress().getPostalCode());
            sender.setCustomerOrder(newCustomerOrder);
            sender.getAddress().setRegion(sender.getAddress().getPostalCode());
            newCustomerOrder.getParties().add(recipient);
            newCustomerOrder.getParties().add(sender);

            newCustomerOrder = customerOrderService.saveOrder(newCustomerOrder);
            OrderDetails orderDetails = customerOrderUtil.generateOrderDetails(newCustomerOrder);
            return new ResponseEntity<>(new ResponseOrder("Success", "Payment session created successfully", orderDetails, clientSecret), HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity<>(new ResponseOrder("Failed", "Payment intent creation failed"), HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(new ResponseOrder("Failed", "Parcel detail input is invalid"), HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity<>(new ResponseOrder("Failed", e.getMessage()), HttpStatus.OK);
        }
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

    @PostMapping("/orders/payment")
    public ResponseEntity<ResponseOrder> completePayment(@RequestBody RequestPayment requestPayment) {
        try {
            CustomerOrder customerOrder = customerOrderService.findByPaymentReference(requestPayment.getPaymentReference());
            Status orderCreatedStatus = statusUtil.generateOrderCreatedStatus();
            orderCreatedStatus.setCustomerOrder(customerOrder);
            Trip pickupTrip = customerOrderUtil.generatePickupTrip();
            pickupTrip.setWarehouse(warehouseService.findById(1L));
            pickupTrip.setCustomerOrder(customerOrder);
            customerOrder.getTrips().add(pickupTrip);
            customerOrder.appendStatus(orderCreatedStatus);
            customerOrderService.saveOrder(customerOrder);
            paymentService.completePayment(requestPayment.getPaymentReference());
            OrderDetails orderDetails = customerOrderUtil.generateOrderDetails(customerOrder);
            return new ResponseEntity<>(new ResponseOrder("Success", "Payment completed and order created successfully", orderDetails), HttpStatus.OK);
        } catch (WarehouseNotFoundException | OrderNotFoundException e) {
            return new ResponseEntity<>(new ResponseOrder("Failed", e.getMessage()), HttpStatus.OK);
        }
    }
}
