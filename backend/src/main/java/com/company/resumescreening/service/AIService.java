package com.company.resumescreening.service;

import org.springframework.stereotype.Service;

@Service
public class AIService {

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
