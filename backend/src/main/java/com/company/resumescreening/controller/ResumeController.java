package com.company.resumescreening.controller;

import com.company.resumescreening.dto.response.AIResponse;
import com.company.resumescreening.service.AIService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final AIService aiService;

    public ResumeController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public AIResponse analyze(@RequestBody String resumeText) {

        AIResponse response = new AIResponse();

        response.setMatchScore(85);
        response.setMatchingSkills("Java, Spring Boot, MySQL");
        response.setMissingSkills("Docker, Kubernetes");
        response.setSummary(aiService.analyzeResume(resumeText));
        response.setRecommendation("Suitable Candidate");

        return response;
    }
}
