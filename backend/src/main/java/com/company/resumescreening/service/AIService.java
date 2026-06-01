package com.company.resumescreening.service;

import com.company.resumescreening.ai.GeminiClient;
import com.company.resumescreening.ai.PromptBuilder;
import com.company.resumescreening.ai.ResponseParser;
import com.company.resumescreening.dto.response.AIResponse;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final GeminiClient geminiClient;
    private final PromptBuilder promptBuilder;
    private final ResponseParser responseParser;

    public AIService(
            GeminiClient geminiClient,
            PromptBuilder promptBuilder,
            ResponseParser responseParser) {

        this.geminiClient = geminiClient;
        this.promptBuilder = promptBuilder;
        this.responseParser = responseParser;
    }

    public AIResponse analyze(String resumeText,
                              String jobDescription) {

        String prompt =
                promptBuilder.buildPrompt(
                        resumeText,
                        jobDescription);

        String rawResponse =
                geminiClient.analyze(prompt);
        return responseParser.parse(rawResponse);
    }
}
