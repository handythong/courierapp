package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.apimodel.OrderDetails;
import com.fdmgroup.courierapp.apimodel.OrderStatus;
import com.fdmgroup.courierapp.apimodel.RequestOrder;
import com.fdmgroup.courierapp.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class CustomerOrderUtil {
    public Parcel generateParcel(RequestOrder requestOrder) throws Exception {
        Parcel newParcel = new Parcel();
        newParcel.setDescription(requestOrder.getParcelDescription());
        newParcel.setHeight(Float.parseFloat(requestOrder.getHeight()));
        newParcel.setLength(Float.parseFloat(requestOrder.getLength()));
        newParcel.setWidth(Float.parseFloat(requestOrder.getWidth()));
        newParcel.setWeight(Float.parseFloat(requestOrder.getWeight()));
        return newParcel;
    }

    public Sender generateOrderSender(RequestOrder requestOrder) {
        Sender newSender = new Sender();
        newSender.setEmail(requestOrder.getFromEmail());
        newSender.setPhoneNo(requestOrder.getFromPhone());
        newSender.setFullName(requestOrder.getFromFullName());
        newSender.setPickupAddress(requestOrder.getFromAddress());
        return newSender;
    }

    public Recipient generateOrderRecipient(RequestOrder requestOrder) {
        Recipient newRecipient = new Recipient();
        newRecipient.setEmail(requestOrder.getToEmail());
        newRecipient.setPhoneNo(requestOrder.getToPhone());
        newRecipient.setFullName(requestOrder.getToFullName());
        newRecipient.setDeliveryAddress(requestOrder.getToAddress());
        return newRecipient;
    }

    public Date generateDeliveryDate() {
        // 5 days from order date
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 5);
        return calendar.getTime();
    }

    public Status generateOrderCreatedStatus() {
        Status status = new Status();
        status.setStatus(StatusEnum.ORDER_CREATED);
        status.setRemarks("Order Created");
        status.setStatusUpdateDate(new Date());
        return status;
    }

    public OrderDetails generateOrderDetails(CustomerOrder customerOrder) {
        OrderDetails newOrderDetails = new OrderDetails();
        newOrderDetails.setOrderId(customerOrder.getId());
        newOrderDetails.setOrderStatus(mappedOrderStatus(customerOrder));
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

    public List<OrderStatus> mappedOrderStatus(CustomerOrder customerOrder) {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        for (Status status: customerOrder.getStatus()) {
            OrderStatus orderStatus = new OrderStatus(status.getStatus().toString(), status.getRemarks(), status.getStatusUpdateDate());
            orderStatuses.add(orderStatus);
        }
        return orderStatuses;
    }
}
