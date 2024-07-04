package com.fdmgroup.courierapp.service;

import com.fdmgroup.courierapp.exception.OrderNotFoundException;
import com.fdmgroup.courierapp.model.CustomerOrder;
import com.fdmgroup.courierapp.model.PaymentSession;
import com.fdmgroup.courierapp.model.Status;
import com.fdmgroup.courierapp.model.StatusEnum;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.*;

@Service
public class PaymentThreadingService {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private StatusService statusService;

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private Runnable checkTimeout = new Runnable() {
        @Override
        public void run() {
            System.out.println("Active Session Cleanup");
            List<PaymentSession> activePaymentReference = paymentService.getActivePaymentReference();
            ListIterator<PaymentSession> iterator = activePaymentReference.listIterator();
            try {
                while (iterator.hasNext()) {
                    PaymentSession paymentSession = iterator.next();
                    if (LocalDateTime.now().isAfter(paymentSession.getTimeoutStamp())) {
                        PaymentIntent paymentIntent = paymentSession.getPaymentIntent();
                        CustomerOrder customerOrder = customerOrderService.findByPaymentReference(paymentIntent.getId());
                        paymentIntent.cancel();
                        Status status = new Status(StatusEnum.CANCELLED,
                                "Payment Timeout",
                                LocalDateTime.now(),
                                customerOrder);
                        statusService.createStatus(status);
                        iterator.remove();
                    }
                }
            } catch (StripeException | OrderNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    };

    public void startTimer() {
        //populate active session when restart
        paymentService.populateActivePaymentReference();
        scheduler.scheduleAtFixedRate(checkTimeout, 0L, 1L, TimeUnit.MINUTES);
    }
}
