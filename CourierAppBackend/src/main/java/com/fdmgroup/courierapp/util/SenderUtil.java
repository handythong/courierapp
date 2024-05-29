package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.apimodel.Sender;
import com.fdmgroup.courierapp.model.CustomerOrder;
import org.springframework.stereotype.Component;

@Component
public class SenderUtil {
    public com.fdmgroup.courierapp.model.Sender mapToModel(Sender sender, Long id, CustomerOrder customerOrder) {
        com.fdmgroup.courierapp.model.Sender mappedSender = new com.fdmgroup.courierapp.model.Sender();
        mappedSender.setId(id);
        mappedSender.setFullName(sender.getFromFullName());
        mappedSender.setEmail(sender.getFromEmail());
        mappedSender.setPhoneNo(sender.getFromPhone());
        mappedSender.setPickupAddress(sender.getFromAddress());
        mappedSender.setOrderItem(customerOrder);
        return mappedSender;
    }
}
