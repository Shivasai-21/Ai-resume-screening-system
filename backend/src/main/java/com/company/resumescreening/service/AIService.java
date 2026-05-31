package com.company.resumescreening.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AIService {

    // Extract text from uploaded resume file
    public String extractText(MultipartFile file) {
        try {
            // TODO: Replace with proper PDF parsing (e.g. Apache PDFBox)
            byte[] bytes = file.getBytes();
            return new String(bytes); // crude fallback
        } catch (IOException e) {
            throw new RuntimeException("Failed to read resume file", e);
        }
    }

    // Analyze resume text (later integrate with AI model)
    public String analyzeResume(String resumeText) {
        return """
               Match Score: 85

               Matching Skills:
               Java, Spring Boot, MySQL

               Missing Skills:
               Kubernetes, Docker

               Recommendation:
               Suitable Candidate
               """;
    }
}

