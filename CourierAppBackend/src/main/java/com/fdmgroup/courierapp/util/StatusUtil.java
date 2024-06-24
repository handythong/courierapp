package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.model.Status;
import com.fdmgroup.courierapp.model.StatusEnum;
import com.fdmgroup.courierapp.model.TripStatusEnum;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StatusUtil {
    public Status statusMapper(String status){
        switch (status) {
            case "ORDER_CREATED":
                return new Status(StatusEnum.ORDER_CREATED);
            case "PROCESSING":
                return new Status(StatusEnum.PROCESSING);
            case "PICKED_UP":
                return new Status(StatusEnum.PICKED_UP);
            case "SORTING":
                return new Status(StatusEnum.SORTING);
            case "READY_FOR_DELIVERY":
                return new Status(StatusEnum.READY_FOR_DELIVERY);
            case "DELIVERING":
                return new Status(StatusEnum.DELIVERING);
            case "DELIVERED":
                return new Status(StatusEnum.DELIVERED);
            case "CANCELLED":
                return new Status(StatusEnum.CANCELLED);
            case "OTHER":
                return new Status(StatusEnum.OTHER);
            default:
                return null;
        }
    }

    public TripStatusEnum tripStatusMapper(String tripStatus) {
        switch (tripStatus) {
            case "UNASSIGNED":
                return TripStatusEnum.UNASSIGNED;
            case "ASSIGNED":
                return TripStatusEnum.ASSIGNED;
            case "RETRIEVED":
                return TripStatusEnum.RETRIEVED;
            case "COMPLETED":
                return TripStatusEnum.COMPLETED;
            default:
                return null;
        }
    }

    public Status generateOrderCreatedStatus() {
        Status status = new Status();
        status.setStatus(StatusEnum.ORDER_CREATED);
        status.setRemarks("Order Created");
        status.setStatusUpdateDate(new Date());
        return status;
    }

    public Status generateAwaitPaymentStatus() {
        Status status = new Status();
        status.setStatus(StatusEnum.AWAITING_PAYMENT);
        status.setRemarks("Awaiting Payment");
        status.setStatusUpdateDate(new Date());
        return status;
    }
}
