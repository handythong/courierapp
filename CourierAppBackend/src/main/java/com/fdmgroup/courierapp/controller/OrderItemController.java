package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.OrderDetails;
import com.fdmgroup.courierapp.apimodel.RequestOrder;
import com.fdmgroup.courierapp.apimodel.ResponseOrder;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.service.CustomerOrderService;
import com.fdmgroup.courierapp.service.RecipientService;
import com.fdmgroup.courierapp.service.SenderService;
import com.fdmgroup.courierapp.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;

@RequestMapping("/orders")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderItemController {
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    ParcelService parcelService;
    @Autowired
    SenderService senderService;
    @Autowired
    RecipientService recipientService;
    
    @PostMapping("/create-order")
    public ResponseEntity<ResponseOrder> login(@RequestBody RequestOrder requestOrder) {

        Parcel newParcel;
        try {
            newParcel = generateParcel(requestOrder);
        } catch (Exception e) {
            return new ResponseEntity<ResponseOrder>(new ResponseOrder("Failed", "Parcel detail input is invalid"), HttpStatus.OK);
        }

        Sender newSender = generateOrderSender(requestOrder);
        Recipient newRecipient = generateOrderRecipient(requestOrder);

        //OrderItem creation
        CustomerOrder newCustomerOrder = new CustomerOrder();
        newCustomerOrder.setOrderDate(new Date());
        newCustomerOrder.setLastUpdated(new Date());
        newCustomerOrder.setDeliveryDate(generateDeliveryDate());
        newCustomerOrder.setSender(newSender);
        newCustomerOrder.setRecipient(newRecipient);
        newCustomerOrder.setParcel(newParcel);
        newCustomerOrder.setStatus(Status.PROCESSING);
        newCustomerOrder = customerOrderService.createOrder(newCustomerOrder);

        OrderDetails orderDetails = generateOrderDetails(newCustomerOrder);
        return new ResponseEntity<ResponseOrder>(new ResponseOrder("Success", "Order Created Successfully", orderDetails), HttpStatus.OK);
    }

    private Parcel generateParcel(RequestOrder requestOrder) throws Exception {
        Parcel newParcel = new Parcel();
        newParcel.setDescription(requestOrder.getParcelDescription());
        newParcel.setHeight(Float.parseFloat(requestOrder.getHeight()));
        newParcel.setLength(Float.parseFloat(requestOrder.getLength()));
        newParcel.setWidth(Float.parseFloat(requestOrder.getWidth()));
        newParcel.setWeight(Float.parseFloat(requestOrder.getWeight()));
        return newParcel;
    }

    private Sender generateOrderSender(RequestOrder requestOrder) {
        Sender newSender = new Sender();
        newSender.setEmail(requestOrder.getFromEmail());
        newSender.setPhoneNo(requestOrder.getFromPhone());
        newSender.setFullName(requestOrder.getFromFullName());
        newSender.setPickupAddress(requestOrder.getFromAddress());
        return newSender;
    }

    private Recipient generateOrderRecipient(RequestOrder requestOrder) {
        Recipient newRecipient = new Recipient();
        newRecipient.setEmail(requestOrder.getToEmail());
        newRecipient.setPhoneNo(requestOrder.getToPhone());
        newRecipient.setFullName(requestOrder.getToFullName());
        newRecipient.setDeliveryAddress(requestOrder.getToAddress());
        return newRecipient;
    }

    private Date generateDeliveryDate() {
        // 3 days from order date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        return calendar.getTime();
    }

    private OrderDetails generateOrderDetails(CustomerOrder customerOrder) {
        OrderDetails newOrderDetails = new OrderDetails();
        newOrderDetails.setOrderId(customerOrder.getId());
        newOrderDetails.setOrderStatus(customerOrder.getStatus().toString());
        newOrderDetails.setDeliveryDate(customerOrder.getDeliveryDate());
        newOrderDetails.setOrderDate(customerOrder.getOrderDate());
        newOrderDetails.setFromAddress(customerOrder.getSender().getPickupAddress());
        newOrderDetails.setFromFullName(customerOrder.getSender().getFullName());
        newOrderDetails.setFromEmail(customerOrder.getSender().getEmail());
        newOrderDetails.setFromPhoneNo(customerOrder.getSender().getPhoneNo());
        newOrderDetails.setToAddress(customerOrder.getRecipient().getDeliveryAddress());
        newOrderDetails.setToFullName(customerOrder.getRecipient().getFullName());
        newOrderDetails.setToEmail(customerOrder.getRecipient().getEmail());
        newOrderDetails.setToPhoneNo(customerOrder.getRecipient().getPhoneNo());
        newOrderDetails.setWeight(customerOrder.getParcel().getWeight());
        newOrderDetails.setWidth(customerOrder.getParcel().getWidth());
        newOrderDetails.setHeight(customerOrder.getParcel().getHeight());
        newOrderDetails.setLength(customerOrder.getParcel().getLength());
        newOrderDetails.setParcelDescription(customerOrder.getParcel().getDescription());
        return newOrderDetails;
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ResponseOrder> retrieveOrderId(@PathVariable("orderId") Long orderId) throws Exception {
        CustomerOrder customerOrder = customerOrderService.findByCustomerOrderId(orderId);
        if (customerOrder == null) {
            return new ResponseEntity<ResponseOrder>(new ResponseOrder("Failed", "Order ID not found in database", null), HttpStatus.OK);
        }
        OrderDetails orderDetails = generateOrderDetails(customerOrder);
        return new ResponseEntity<ResponseOrder>(new ResponseOrder("Success", "Order Retrieved Successfully", orderDetails), HttpStatus.OK);
    }
}
