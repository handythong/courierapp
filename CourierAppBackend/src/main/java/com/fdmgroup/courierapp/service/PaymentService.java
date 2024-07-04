package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.apimodel.RequestPayment;
import com.fdmgroup.courierapp.model.CustomerOrder;
import com.fdmgroup.courierapp.model.PaymentSession;
import com.fdmgroup.courierapp.model.StatusEnum;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Value("${stripeSecretKey}")
    private String secretKey;
    private List<PaymentSession> activePaymentReference = new ArrayList<>();

    @Autowired
    private CustomerOrderService customerOrderService;

    public PaymentIntent generateStripePaymentIntent(long amount) throws StripeException {
        Stripe.apiKey = secretKey;
        List<String> paymentMethodType = new ArrayList<>();
        paymentMethodType.add("card");
        paymentMethodType.add("paynow");
        PaymentIntentCreateParams params =
                PaymentIntentCreateParams.builder()
                        .setAmount(amount)
                        .setCurrency("sgd")
                        .addAllPaymentMethodType(paymentMethodType)
                        .build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        activePaymentReference.add(new PaymentSession(paymentIntent, LocalDateTime.now().plusMinutes(15)));
        return paymentIntent;
    }

    public void completePayment(String paymentReference) {
        activePaymentReference.removeIf(paymentSession -> paymentSession.getPaymentIntent().getId().equals(paymentReference));
    }

    public List<PaymentSession> getActivePaymentReference() {
        return activePaymentReference;
    }

    public void populateActivePaymentReference() {
        Stripe.apiKey = secretKey;
        List<CustomerOrder> customerOrders = customerOrderService.getAllOrder();
        customerOrders = customerOrders.stream()
                .filter(customerOrder -> customerOrder.getStatuses().size() == 1)
                .toList();
        for (CustomerOrder customerOrder : customerOrders) {
            try {
                PaymentIntent paymentIntent = PaymentIntent.retrieve(customerOrder.getPaymentReference());
                activePaymentReference.add(new PaymentSession(paymentIntent, LocalDateTime.now().plusMinutes(15)));
            } catch (StripeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
