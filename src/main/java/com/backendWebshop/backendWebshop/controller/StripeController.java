package com.backendWebshop.backendWebshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    private static final Logger log = LoggerFactory.getLogger(StripeController.class);

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<Map<String, String>> createPaymentIntent(@RequestBody Map<String, Object> request) {
        try {
            log.info("Received payment intent request: {}", request);
            double amountInDollars = Double.parseDouble(request.get("amount").toString());
            long amountInCents = (long) (amountInDollars * 100);

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amountInCents)
                .setCurrency("usd")
                .build();
            PaymentIntent intent = PaymentIntent.create(params);

            Map<String, String> responseData = new HashMap<>();
            responseData.put("clientSecret", intent.getClientSecret());

            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            log.error("Error creating payment intent: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
