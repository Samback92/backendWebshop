package com.backendWebshop.backendWebshop.service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertNotNull;



@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")
@SpringBootTest(classes = { StripeService.class })
public class StripeServiceTest {

    @InjectMocks
    private StripeService stripeService;

    @Value("${stripe.apiKey}") 
    private String stripeApiKey;

    @BeforeEach 
    public void setUp() { 
        // API-nyckeln från konfigurationsfilen
        Stripe.apiKey = stripeApiKey;
    }

    @Test
    public void testCreatePaymentIntent() throws Exception {
        // Arrange
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(1000L) // $10.00
            .setCurrency("usd")
            .build();

        // Act
        PaymentIntent paymentIntent = stripeService.createPaymentIntent(params);

        // Assert
        assertNotNull(paymentIntent);
        assertNotNull(paymentIntent.getClientSecret());
    }
}
