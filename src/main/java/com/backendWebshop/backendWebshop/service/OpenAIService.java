package com.backendWebshop.backendWebshop.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {

    @Value("${spring.ai.openai.api-key}")
    private String openaiApiKey;

    private final RestTemplate restTemplate;

    public OpenAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Object> getResponse(String query) {
        String apiUrl = "https://api.openai.com/v1/engines/gpt-3.5-turbo-instruct/completions"; // Uppdaterad modell

        Map<String, Object> payload = new HashMap<>();
        payload.put("prompt", query);
        payload.put("max_tokens", 100);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}