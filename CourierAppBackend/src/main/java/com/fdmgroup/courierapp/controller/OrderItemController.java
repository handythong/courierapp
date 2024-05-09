package com.fdmgroup.courierapp.controller;

import com.fdmgroup.courierapp.apimodel.OrderDetails;
import com.fdmgroup.courierapp.apimodel.RequestOrder;
import com.fdmgroup.courierapp.apimodel.ResponseOrder;
import com.fdmgroup.courierapp.model.*;
import com.fdmgroup.courierapp.service.OrderItemService;
import com.fdmgroup.courierapp.service.OrderRecipientService;
import com.fdmgroup.courierapp.service.OrderSenderService;
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
    OrderItemService orderItemService;
    @Autowired
    ParcelService parcelService;
    @Autowired
    OrderSenderService orderSenderService;
    @Autowired
    OrderRecipientService orderRecipientService;
    
    @PostMapping("/create-order")
    public ResponseEntity<ResponseOrder> login(@RequestBody RequestOrder requestOrder) {

        Parcel newParcel;
        try {
            newParcel = generateParcel(requestOrder);
        } catch (Exception e) {
            return new ResponseEntity<ResponseOrder>(new ResponseOrder("Failed", "Parcel detail input is invalid"), HttpStatus.OK);
        }

        OrderSender newOrderSender = generateOrderSender(requestOrder);
        OrderRecipient newOrderRecipient = generateOrderRecipient(requestOrder);

        //OrderItem creation
        OrderItem newOrderItem = new OrderItem();
        newOrderItem.setOrderDate(new Date());
        newOrderItem.setLastUpdated(new Date());
        newOrderItem.setDeliveryDate(generateDeliveryDate());
        newOrderItem.setOrderSender(newOrderSender);
        newOrderItem.setOrderRecipient(newOrderRecipient);
        newOrderItem.setParcel(newParcel);
        newOrderItem.setStatus(Status.PROCESSING);
        newOrderItem = orderItemService.createOrder(newOrderItem);

        OrderDetails orderDetails = generateOrderDetails(newOrderItem);
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

    private OrderSender generateOrderSender(RequestOrder requestOrder) {
        OrderSender newOrderSender = new OrderSender();
        newOrderSender.setSenderEmail(requestOrder.getFromEmail());
        newOrderSender.setSenderPhoneNo(requestOrder.getFromPhone());
        newOrderSender.setSenderFullName(requestOrder.getFromFullName());
        newOrderSender.setPickupAddress(requestOrder.getFromAddress());
        return newOrderSender;
    }

    private OrderRecipient generateOrderRecipient(RequestOrder requestOrder) {
        OrderRecipient newOrderRecipient = new OrderRecipient();
        newOrderRecipient.setRecipientEmail(requestOrder.getToEmail());
        newOrderRecipient.setRecipientPhoneNo(requestOrder.getToPhone());
        newOrderRecipient.setRecipientFullName(requestOrder.getToFullName());
        newOrderRecipient.setDeliveryAddress(requestOrder.getToAddress());
        return newOrderRecipient;
    }

    private Date generateDeliveryDate() {
        // 3 days from order date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        return calendar.getTime();
    }

    private OrderDetails generateOrderDetails(OrderItem orderItem) {
        OrderDetails newOrderDetails = new OrderDetails();
        newOrderDetails.setOrderId(orderItem.getOrderId());
        newOrderDetails.setOrderStatus(orderItem.getStatus().toString());
        newOrderDetails.setDeliveryDate(orderItem.getDeliveryDate());
        newOrderDetails.setOrderDate(orderItem.getOrderDate());
        newOrderDetails.setFromAddress(orderItem.getOrderSender().getPickupAddress());
        newOrderDetails.setFromFullName(orderItem.getOrderSender().getSenderFullName());
        newOrderDetails.setFromEmail(orderItem.getOrderSender().getSenderEmail());
        newOrderDetails.setFromPhoneNo(orderItem.getOrderSender().getSenderPhoneNo());
        newOrderDetails.setToAddress(orderItem.getOrderRecipient().getDeliveryAddress());
        newOrderDetails.setToFullName(orderItem.getOrderRecipient().getRecipientFullName());
        newOrderDetails.setToEmail(orderItem.getOrderRecipient().getRecipientEmail());
        newOrderDetails.setToPhoneNo(orderItem.getOrderRecipient().getRecipientPhoneNo());
        newOrderDetails.setWeight(orderItem.getParcel().getWeight());
        newOrderDetails.setWidth(orderItem.getParcel().getWidth());
        newOrderDetails.setHeight(orderItem.getParcel().getHeight());
        newOrderDetails.setLength(orderItem.getParcel().getLength());
        newOrderDetails.setParcelDescription(orderItem.getParcel().getDescription());
        return newOrderDetails;
    }
}
