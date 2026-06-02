package com.company.resumescreening.service;

import com.company.resumescreening.pdf.ResumeParser;
import com.company.resumescreening.ai.GeminiClient;
import com.company.resumescreening.ai.PromptBuilder;
import com.company.resumescreening.ai.ResponseParser;
import com.company.resumescreening.dto.response.AIResponse;
import org.springframework.stereotype.Service;

@Service
public class AIService {

    private final ResumeParser resumeParser;
    private final GeminiClient geminiClient;
    private final PromptBuilder promptBuilder;
    private final ResponseParser responseParser;

    public AIService(
            GeminiClient geminiClient,
            PromptBuilder promptBuilder,
            ResponseParser responseParser,
            ResumeParser resumeParser) {

        this.geminiClient = geminiClient;
        this.promptBuilder = promptBuilder;
        this.responseParser = responseParser;
        this.resumeParser = resumeParser;
    }

    public AIResponse analyze(String resumeText, String jobDescription) {
        String prompt = promptBuilder.buildPrompt(resumeText, jobDescription);
        String rawResponse = geminiClient.analyze(prompt);
        System.out.println("========== GEMINI RESPONSE ==========");
	System.out.println(rawResponse);
	System.out.println("=====================================");
       	AIResponse response =
        responseParser.parse(rawResponse);

	response.setCandidateEmail(
        	resumeParser.extractEmail(resumeText));

	response.setCandidatePhone(
        	resumeParser.extractPhone(resumeText));

	return response;
    }
}

