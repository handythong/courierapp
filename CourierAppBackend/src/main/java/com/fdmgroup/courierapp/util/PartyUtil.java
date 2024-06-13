package com.fdmgroup.courierapp.util;

import com.fdmgroup.courierapp.apimodel.Recipient;
import com.fdmgroup.courierapp.apimodel.Sender;
import com.fdmgroup.courierapp.model.Address;
import com.fdmgroup.courierapp.model.CustomerOrder;
import com.fdmgroup.courierapp.model.Party;
import com.fdmgroup.courierapp.model.PartyEnum;
import com.fdmgroup.courierapp.service.PartyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartyUtil {
    @Autowired
    PartyService partyService;
    private final Logger logger = LogManager.getLogger();

    public Party mapRecipient(Recipient recipient, CustomerOrder customerOrder) {

        Party partyRecipient = customerOrder.getParties().stream()
                .filter(party -> party.getPartyType().equals(PartyEnum.RECIPIENT))
                .findFirst().orElseGet(null);
//        Party partyRecipient = partyService.findById(recipientId);
//        if (partyRecipient.getPartyType() != PartyEnum.RECIPIENT) {
//            throw new PartyNotFoundException("Recipient not found");
//        }
        Address newAddress = mapAddress(partyRecipient.getAddress(), recipient.getToAddress());
        partyRecipient.setFullName(recipient.getToFullName());
        partyRecipient.setEmail(recipient.getToEmail());
        partyRecipient.setPhoneNo(recipient.getToPhone());
        partyRecipient.setAddress(newAddress);
        return partyRecipient;
    }

    public Party mapSender(Sender sender, CustomerOrder customerOrder) {
        Party partySender = customerOrder.getParties().stream()
                .filter(party -> party.getPartyType().equals(PartyEnum.SENDER))
                .findFirst().orElseGet(null);
//        Party partySender = partyService.findById(senderId);
//        if (partySender.getPartyType() != PartyEnum.SENDER) {
//            throw new PartyNotFoundException("Sender not found");
//        }
        Address newAddress = mapAddress(partySender.getAddress(), sender.getFromAddress());
        partySender.setFullName(sender.getFromFullName());
        partySender.setEmail(sender.getFromEmail());
        partySender.setPhoneNo(sender.getFromPhone());
        partySender.setAddress(newAddress);
        return partySender;
    }

    public Address mapAddress(Address existingAddress, Address newAddress) {
        existingAddress.setAddress(newAddress.getAddress());
        existingAddress.setPostalCode(newAddress.getPostalCode());
        existingAddress.setCountry(newAddress.getCountry());
        existingAddress.setCity(newAddress.getCity());
        return existingAddress;
    }
}
