package com.backendWebshop.backendWebshop.controller;

import com.backendWebshop.backendWebshop.service.StripeService;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;




/*JAG HAR FÖRSÖKT LÖSA DETTA TEST I EN EVIGHET! JAG SKA ÅTERKOMNMA TILL DENNA NÄR DET ANDRA ÄR KLART!!!*/

@WebMvcTest(StripeController.class) // Anger att detta är en testklass för WebMVC, fokuserad på StripeController. 
@ExtendWith(MockitoExtension.class) // Utökar JUnit5 med Mockito-funktioner. 
@TestPropertySource(locations = "classpath:application-test.properties")
public class StripeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StripeService stripeService;

    @InjectMocks
    private StripeController stripeController;

    @Value("${stripe.apiKey}") 
    private String stripeApiKey;

    @BeforeEach
    public void setup() {
        // API-nyckeln från konfigurationsfilen
        Stripe.apiKey = stripeApiKey;

        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(stripeController).build();
    }

    @Test
    public void testCreatePaymentIntent() throws Exception {
        // Arrange
        PaymentIntent mockPaymentIntent = Mockito.mock(PaymentIntent.class); 
        when(mockPaymentIntent.getClientSecret()).thenReturn("test_client_secret"); 
        when(stripeService.createPaymentIntent(any(PaymentIntentCreateParams.class))).thenReturn(mockPaymentIntent);

        // Create request payload
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("amount", 100.0);

        // Act & Assert
        mockMvc.perform(post("/api/stripe/create-payment-intent")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amount\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientSecret").value("test_client_secret"));
    }
}
