package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.apimodel.RequestPayment;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {

    @Value("${stripeSecretKey}")
    private String secretKey;

    public PaymentIntent generateStripePaymentIntent(long amount) throws StripeException {
        Stripe.apiKey = secretKey;
        List<String> paymentMethodType = new ArrayList<>();
        paymentMethodType.add("card");
        paymentMethodType.add("grabpay");
        paymentMethodType.add("paynow");
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount)
                        .setCurrency("sgd")
                        .addAllPaymentMethodType(paymentMethodType)
                        .build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent;
    }
}
