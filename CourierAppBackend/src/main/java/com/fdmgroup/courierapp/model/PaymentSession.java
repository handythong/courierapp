package com.fdmgroup.courierapp.model;

import com.stripe.model.PaymentIntent;

import java.time.LocalDateTime;

public class PaymentSession {
    private PaymentIntent paymentIntent;
    private LocalDateTime timeoutStamp;

    public PaymentSession(PaymentIntent paymentIntent, LocalDateTime timeoutStamp) {
        this.paymentIntent = paymentIntent;
        this.timeoutStamp = timeoutStamp;
    }

    public PaymentIntent getPaymentIntent() {
        return paymentIntent;
    }

    public void setPaymentIntent(PaymentIntent paymentIntent) {
        this.paymentIntent = paymentIntent;
    }

    public LocalDateTime getTimeoutStamp() {
        return timeoutStamp;
    }

    public void setTimeoutStamp(LocalDateTime timeoutStamp) {
        this.timeoutStamp = timeoutStamp;
    }
}
