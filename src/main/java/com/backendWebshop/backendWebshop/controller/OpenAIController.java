package com.backendWebshop.backendWebshop.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backendWebshop.backendWebshop.service.OpenAIService;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> getResponseFromOpenAI(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        Map<String, Object> response = openAIService.getResponse(query);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
