package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.model.Status;
import com.fdmgroup.courierapp.model.StatusEnum;
import org.springframework.stereotype.Component;

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
        }
        return null;
    }
}
