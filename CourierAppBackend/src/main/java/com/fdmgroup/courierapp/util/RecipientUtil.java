package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.apimodel.Recipient;
import com.fdmgroup.courierapp.model.CustomerOrder;
import org.springframework.stereotype.Component;

@Component
public class RecipientUtil {
    public com.fdmgroup.courierapp.model.Recipient mapToModel(Recipient recipient, Long id, CustomerOrder customerOrder) {
        com.fdmgroup.courierapp.model.Recipient mappedRecipient = new com.fdmgroup.courierapp.model.Recipient();
        mappedRecipient.setId(id);
        mappedRecipient.setFullName(recipient.getToFullName());
        mappedRecipient.setEmail(recipient.getToEmail());
        mappedRecipient.setPhoneNo(recipient.getToPhone());
        mappedRecipient.setDeliveryAddress(recipient.getToAddress());
        mappedRecipient.setOrderItem(customerOrder);
        return mappedRecipient;
    }
}
