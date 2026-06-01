package com.company.resumescreening.ai;

import com.company.resumescreening.dto.response.AIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ResponseParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public AIResponse parse(String response) throws Exception {

        response = response
                .replace("```json", "")
                .replace("```", "")
                .trim();

        return mapper.readValue(response, AIResponse.class);
    }
}
