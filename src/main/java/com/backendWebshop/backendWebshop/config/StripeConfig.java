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

    @Value("${stripe.apiKey:#{null}}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        if (stripeApiKey != null) {
            Stripe.apiKey = stripeApiKey;
            log.info("Stripe API key initialized: " + (stripeApiKey != null));
        } else {
            log.error("Stripe API key is not set. Please configure the 'stripe.apiKey' environment variable.");
        }
    }
}
