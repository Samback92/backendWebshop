package com.backendWebshop.backendWebshop.service;

import com.backendWebshop.backendWebshop.model.Conversation;
import com.backendWebshop.backendWebshop.repository.ConversationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


/*JAG FÅR INTE TILL DET!! JAG SKA ÅTERKOMNMA TILL DENNA NÄR DET ANDRA ÄR KLART!!!*/


@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties")
public class OpenAIServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ConversationRepository conversationRepository;

    @InjectMocks
    private OpenAIService openAIService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        openAIService = new OpenAIService(restTemplate, conversationRepository);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetResponse() {
        // Arrange
        String query = "Hello";
        String apiUrl = "https://api.openai.com/v1/engines/gpt-3.5-turbo-instruct/completions";

        Map<String, Object> payload = new HashMap<>();
        payload.put("prompt", query);
        payload.put("max_tokens", 100);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer test_api_key");
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        Map<String, Object> mockResponseBody = new HashMap<>();
        Map<String, Object> choice = new HashMap<>();
        choice.put("text", "This is a response");
        mockResponseBody.put("choices", List.of(choice));

        ResponseEntity<Map<String, Object>> mockResponse = ResponseEntity.ok(mockResponseBody);
        when(restTemplate.exchange(eq(apiUrl), eq(HttpMethod.POST), eq(entity),
                any(ParameterizedTypeReference.class))).thenReturn(mockResponse);

        // Act
        Map<String, Object> response = openAIService.getResponse(query);

        // Assert
        assertNotNull(response);
        verify(conversationRepository, times(1)).save(any(Conversation.class));
    }
}
