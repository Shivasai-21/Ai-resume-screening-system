package com.company.resumescreening.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIResponse {

    // Candidate details
    private String candidateName;
    private String candidateEmail;
    private String candidatePhone;

    // Analysis results
    private Integer matchScore;
    private List<String> matchingSkills;
    private List<String> missingSkills;

    // AI-generated content
    private String summary;
    private String recommendation;
    private List<String> interviewQuestions;
}

