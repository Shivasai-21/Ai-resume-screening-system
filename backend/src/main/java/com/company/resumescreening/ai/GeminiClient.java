package com.company.resumescreening.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GeminiClient {

    private final WebClient webClient;
    private final String apiKey;
    private final ObjectMapper objectMapper;

    // 🔥 CACHE (in-memory)
    private final Map<String, String> cache = new ConcurrentHashMap<>();

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

        // 🔥 1. CACHE CHECK
        if (cache.containsKey(prompt)) {
            return cache.get(prompt);
        }

        Map<String, Object> body = Map.of(
                "contents", new Object[]{
                        Map.of(
                                "parts", new Object[]{
                                        Map.of("text", prompt)
                                }
                        )
                }
        );

        try {
            String response = webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1beta/models/gemini-2.5-flash:generateContent")
                            .queryParam("key", apiKey)
                            .build()
                    )
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)

                    // 🔥 2. RETRY for rate limits
                    .retryWhen(
                            Retry.backoff(3, Duration.ofSeconds(2))
                                    .filter(this::isRetryable)
                    )

                    // 🔥 3. FALLBACK (no crash)
                    .onErrorResume(e -> Mono.just(
                            "{\"candidates\":[{\"content\":{\"parts\":[{\"text\":\"AI service temporarily unavailable. Please try again later.\"}]}}]}"
                    ))
                    .block();

            String result = parseResponse(response);

            // 🔥 4. STORE IN CACHE
            cache.put(prompt, result);

            return result;

        } catch (Exception e) {
            return "AI analysis failed. Please try again.";
        }
    }

    // 🔥 Retry only for safe errors
    private boolean isRetryable(Throwable e) {
        return e instanceof WebClientResponseException.TooManyRequests
                || e instanceof WebClientResponseException.ServiceUnavailable
                || e instanceof WebClientResponseException.GatewayTimeout;
    }

    // 🔥 Safe JSON parsing
    private String parseResponse(String response) {
        try {
            JsonNode root = objectMapper.readTree(response);

            return root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        } catch (Exception e) {
            return "Failed to parse AI response.";
        }
    }
}
