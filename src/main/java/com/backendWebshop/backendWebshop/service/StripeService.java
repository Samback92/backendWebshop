package com.backendWebshop.backendWebshop.service;

import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    public PaymentIntent createPaymentIntent(PaymentIntentCreateParams params) throws Exception {
        return PaymentIntent.create(params);
    }
}
