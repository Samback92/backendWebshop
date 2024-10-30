package com.backendWebshop.backendWebshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backendWebshop.backendWebshop.service.OpenAIService;


@WebMvcTest(OpenAIController.class) // Anger att detta är en testklass för WebMVC, fokuserad på OpenAIController. 
@ExtendWith(MockitoExtension.class) // Utökar JUnit5 med Mockito-funktioner. 
public class OpenAIControllerTest { 
    
    @Autowired 
    private MockMvc mockMvc; // MockMvc används för att simulera HTTP-förfrågningar och svar.

    @MockBean 
    private OpenAIService openAIService; // Mockar OpenAIService så att vi kan styra dess beteende under testerna.

    @InjectMocks 
    private OpenAIController openAIController; // Injicerar den mockade OpenAIService i OpenAIController.

    @BeforeEach 
    public void setup() { 
        mockMvc = MockMvcBuilders.standaloneSetup(openAIController).build(); // Initierar MockMvc med OpenAIController. 
    }

    @Test 
    public void testGetResponseFromOpenAI() throws Exception { 
        // Skapar en mock-respons som ska returneras av OpenAIService. 
        Map<String, Object> mockResponse = new HashMap<>(); 
        mockResponse.put("result", "This is a test response"); 
        when(openAIService.getResponse("test query")).thenReturn(mockResponse); // Anger beteendet för den mockade metoden. 

        // Skapar payloaden för POST-förfrågan. 
        Map<String, String> requestPayload = new HashMap<>(); 
        requestPayload.put("query", "test query");

        // Utför POST-förfrågan och verifierar svar och HTTP-status. 
        mockMvc.perform(post("/api/openai") 
            .contentType(MediaType.APPLICATION_JSON) 
            .content("{\"query\":\"test query\"}")) 
            .andExpect(status().isOk()) .andExpect(jsonPath("$.result").value("This is a test response")); 
    } 
        
    @Test 
    public void testGetResponseFromOpenAI_InternalServerError() throws Exception { 
        // Mockar så att OpenAIService returnerar null (vilket leder till 500-fel). 
        when(openAIService.getResponse("test query")).thenReturn(null); 
        
        // Skapar payloaden för POST-förfrågan. 
        Map<String, String> requestPayload = new HashMap<>(); 
        requestPayload.put("query", "test query"); 
        
        // Utför POST-förfrågan och verifierar HTTP-status. 
        mockMvc.perform(post("/api/openai") 
            .contentType(MediaType.APPLICATION_JSON) 
            .content("{\"query\":\"test query\"}")) 
            .andExpect(status().isInternalServerError()); 
        }
}
