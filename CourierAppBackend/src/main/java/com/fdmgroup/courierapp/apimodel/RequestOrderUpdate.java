package com.fdmgroup.courierapp.apimodel;

import com.fdmgroup.courierapp.apimodel.Recipient;
import com.fdmgroup.courierapp.apimodel.Sender;

public class RequestOrderUpdate {
    private Recipient recipient;
    private Sender sender;

    public RequestOrderUpdate() {
    }

    public RequestOrderUpdate(Recipient recipient, Sender sender) {
        this.recipient = recipient;
        this.sender = sender;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "RequestOrderUpdate{" +
                "sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }

    public boolean areAllFieldsPresent() {
        return isRecipientValid() && isSenderValid();
    }

    private boolean isRecipientValid() {
        if (recipient == null) {
            return false;
        }
        return recipient.getToFullName() != null && !recipient.getToFullName().trim().isEmpty() &&
                recipient.getToEmail() != null && !recipient.getToEmail().trim().isEmpty() &&
                recipient.getToPhone() != null && !recipient.getToPhone().trim().isEmpty() &&
                recipient.getToAddress() != null && !recipient.getToAddress().trim().isEmpty();
    }

    private boolean isSenderValid() {
        if (sender == null) {
            return false;
        }
        return sender.getFromFullName() != null && !sender.getFromFullName().trim().isEmpty() &&
                sender.getFromEmail() != null && !sender.getFromEmail().trim().isEmpty() &&
                sender.getFromPhone() != null && !sender.getFromPhone().trim().isEmpty() &&
                sender.getFromAddress() != null && !sender.getFromAddress().trim().isEmpty();
    }
}
