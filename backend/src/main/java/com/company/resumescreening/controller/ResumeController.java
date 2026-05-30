package com.company.resumescreening.controller;

import com.company.resumescreening.dto.response.AIResponse;
import com.company.resumescreening.service.AIService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final AIService aiService;

    public ResumeController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/analyze")
    public AIResponse analyze(@RequestParam("file") MultipartFile file,
                              @RequestParam("jobDescription") String jobDescription) {

        // Extract text from resume PDF
        String resumeText = aiService.extractText(file);

        // Call AI service to analyze
        AIResponse response = new AIResponse();
        response.setCandidateName("John Doe"); // Example, parse from resume
        response.setCandidateEmail("john@example.com");
        response.setCandidatePhone("1234567890");

        response.setMatchScore(85);
        response.setMatchingSkills(Arrays.asList("Java", "Spring Boot", "MySQL"));
        response.setMissingSkills(Arrays.asList("Docker", "Kubernetes"));

        response.setInterviewQuestions(List.of(
                "Can you describe your experience with Docker?",
                "How would you design a CI/CD pipeline?",
                "What strategies do you use for scaling Spring Boot apps?"
        ));

        return response;
    }
}

