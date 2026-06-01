package com.company.resumescreening.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
public class GeminiClient {

    private final WebClient webClient;
    private final String apiKey;
    private final ObjectMapper objectMapper;

    public GeminiClient(
            WebClient.Builder webClientBuilder,
            @Value("${app.ai.api-key}") String apiKey,
            ObjectMapper objectMapper
    ) {
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com")
                .build();

        this.apiKey = apiKey;
        this.objectMapper = objectMapper;
    }

    public String analyze(String prompt) {

        Map<String, Object> body = Map.of(
                "contents", new Object[] {
                        Map.of(
                                "parts", new Object[] {
                                        Map.of("text", prompt)
                                }
                        )
                }
        );

        String response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1beta/models/gemini-2.5-flash:generateContent")
                        .queryParam("key", apiKey)
                        .build()
                )
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .onStatus(status -> status.isError(),
                        clientResponse -> clientResponse.bodyToMono(String.class)
                                .map(error -> new RuntimeException("Gemini API Error: " + error))
                )
                .bodyToMono(String.class)
                .block();

        try {
            JsonNode root = objectMapper.readTree(response);

            JsonNode textNode = root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text");

            return textNode.asText();

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini response", e);
        }
    }
}
