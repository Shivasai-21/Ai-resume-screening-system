package com.company.resumescreening.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class AIResponse {

    private String candidateName;
    private String candidateEmail;
    private String candidatePhone;

    private Integer matchScore;

    @JsonAlias({"matchedSkills"})
    private List<String> matchingSkills;

    private List<String> missingSkills;

    private List<String> strengths;
    private List<String> weaknesses;

    private String recommendation;

    private List<String> interviewQuestions;
}
