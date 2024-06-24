package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.apimodel.OrderDashboardDetails;
import com.fdmgroup.courierapp.apimodel.OrderDetails;
import com.fdmgroup.courierapp.apimodel.OrderStatus;
import com.fdmgroup.courierapp.apimodel.RequestOrder;
import com.fdmgroup.courierapp.model.*;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
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

    public Party generateOrderRecipient(RequestOrder requestOrder) {
        Party recipient = new Party();
        recipient.setEmail(requestOrder.getToEmail());
        recipient.setPhoneNo(requestOrder.getToPhoneNo());
        recipient.setFullName(requestOrder.getToFullName());
        recipient.setAddress(requestOrder.getToAddress());
        recipient.setPartyType(PartyEnum.RECIPIENT);
        return recipient;
    }

    public Party generateOrderSender(RequestOrder requestOrder) {
        Party sender = new Party();
        sender.setEmail(requestOrder.getFromEmail());
        sender.setPhoneNo(requestOrder.getFromPhoneNo());
        sender.setFullName(requestOrder.getFromFullName());
        sender.setAddress(requestOrder.getFromAddress());
        sender.setPartyType(PartyEnum.SENDER);
        return sender;
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

    public Trip generatePickupTrip() {
        Trip trip = new Trip();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 2);

        trip.setRoute(RouteEnum.INBOUND);
        trip.setTripDate(calendar.getTime());
        trip.setTripStatus(TripStatusEnum.UNASSIGNED);
        return trip;
    }

    public OrderDetails generateOrderDetails(CustomerOrder customerOrder) {
        OrderDetails newOrderDetails = new OrderDetails();
        Party recipient = customerOrder.getParties().stream()
                .filter(party -> party.getPartyType().equals(PartyEnum.RECIPIENT))
                .findFirst()
                .orElse(null);
        Party sender = customerOrder.getParties().stream()
                .filter(party -> party.getPartyType().equals(PartyEnum.SENDER))
                .findFirst()
                .orElse(null);
        newOrderDetails.setOrderId(customerOrder.getId());
        newOrderDetails.setOrderStatus(mappedOrderStatus(customerOrder));
        newOrderDetails.setDeliveryDate(customerOrder.getDeliveryDate());
        newOrderDetails.setOrderDate(customerOrder.getOrderDate());
        newOrderDetails.setFromAddress(sender.getAddress());
        newOrderDetails.setFromFullName(sender.getFullName());
        newOrderDetails.setFromEmail(sender.getEmail());
        newOrderDetails.setFromPhoneNo(sender.getPhoneNo());
        newOrderDetails.setToAddress(recipient.getAddress());
        newOrderDetails.setToFullName(recipient.getFullName());
        newOrderDetails.setToEmail(recipient.getEmail());
        newOrderDetails.setToPhoneNo(recipient.getPhoneNo());
        newOrderDetails.setWeight(customerOrder.getParcel().getWeight());
        newOrderDetails.setWidth(customerOrder.getParcel().getWidth());
        newOrderDetails.setHeight(customerOrder.getParcel().getHeight());
        newOrderDetails.setLength(customerOrder.getParcel().getLength());
        newOrderDetails.setParcelDescription(customerOrder.getParcel().getDescription());
        return newOrderDetails;
    }

    public List<OrderStatus> mappedOrderStatus(CustomerOrder customerOrder) {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        for (Status status: customerOrder.getStatuses()) {
            OrderStatus orderStatus = new OrderStatus(status.getStatus().toString(), status.getRemarks(), status.getStatusUpdateDate());
            orderStatuses.add(orderStatus);
        }
        return orderStatuses;
    }
    
	public OrderDashboardDetails generateOrderDashboardDetails(CustomerOrder customerOrder) {
		OrderDashboardDetails odb = new OrderDashboardDetails();
        Party recipient = customerOrder.getParties().stream()
                .filter(party -> party.getPartyType().equals(PartyEnum.RECIPIENT))
                .findFirst()
                .orElse(null);
        Party sender = customerOrder.getParties().stream()
                .filter(party -> party.getPartyType().equals(PartyEnum.SENDER))
                .findFirst()
                .orElse(null);
		odb.setOrderId(customerOrder.getId());
		odb.setToFullName(recipient.getFullName());
		odb.setFromFullName(sender.getFullName());
		odb.setFromAddress(sender.getAddress());
		odb.setToAddress(recipient.getAddress());
		
		String currentStatus = customerOrder.getStatuses().stream()
				.max(Comparator.comparing(Status::getStatusUpdateDate))
				.map(Status::getStatus)
				.map(Enum::toString)
				.orElse("");

		odb.setCurrentStatus(currentStatus.toString());
		
		odb.setOrderDate(customerOrder.getOrderDate()
						.toInstant()
						.atZone(ZoneId.systemDefault())
						.toLocalDate());
		
		odb.setDeliveryDate(customerOrder.getDeliveryDate()
				.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate());
		
		return odb;
	}
}
