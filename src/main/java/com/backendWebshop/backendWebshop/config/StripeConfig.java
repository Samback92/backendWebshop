package com.backendWebshop.backendWebshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.stripe.Stripe;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class StripeConfig {

    private static final Logger log = LoggerFactory.getLogger(StripeConfig.class);

    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
        log.info("Stripe API key initialized: " + (stripeApiKey != null));
    }
}