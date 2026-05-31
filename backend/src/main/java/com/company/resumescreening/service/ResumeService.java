package com.company.resumescreening.service;

import com.company.resumescreening.entity.Candidate;
import com.company.resumescreening.entity.ResumeAnalysis;
import com.company.resumescreening.repository.ResumeAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeAnalysisRepository repository;

    public ResumeAnalysis analyze(Candidate candidate) {

        ResumeAnalysis analysis = ResumeAnalysis.builder()
                .candidate(candidate)
                .matchScore(75)
                .matchingSkills("Java, Spring Boot, MySQL")
                .missingSkills("Docker, Kubernetes")
                .summary("Candidate is suitable for backend developer role.")
                .interviewQuestions(
                        "Explain Spring Boot.\nWhat is JPA?"
                )
                .build();

        return repository.save(analysis);
    }
}
