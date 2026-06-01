package com.company.resumescreening.service;

import com.company.resumescreening.dto.response.AIResponse;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AIService {

    public AIResponse analyze(String resumeText, String jobDescription) {
        // Extract candidate details (simple regex demo)
        String name = extractName(resumeText);
        String email = extractEmail(resumeText);
        String phone = extractPhone(resumeText);

        // Extract skills
        List<String> resumeSkills = skillExtractor(resumeText);
        List<String> jdSkills = skillExtractor(jobDescription);

        // Compare skills
        List<String> matching = findMatches(resumeSkills, jdSkills);
        List<String> missing = findMissing(jdSkills, resumeSkills);

        int score = jdSkills.isEmpty() ? 0 : (int) ((double) matching.size() / jdSkills.size() * 100);

        // Generate interview questions
        List<String> questions = generateQuestions(missing);

        AIResponse response = new AIResponse();
        response.setCandidateName(name);
        response.setCandidateEmail(email);
        response.setCandidatePhone(phone);
        response.setMatchScore(score);
        response.setMatchingSkills(matching);
        response.setMissingSkills(missing);
        response.setInterviewQuestions(questions);

        return response;
    }

    private String extractName(String text) {
        // crude fallback: first line
        return text.split("\n")[0];
    }

    private String extractEmail(String text) {
        Matcher matcher = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+").matcher(text);
        return matcher.find() ? matcher.group() : "N/A";
    }

    private String extractPhone(String text) {
        Matcher matcher = Pattern.compile("\\d{10}").matcher(text);
        return matcher.find() ? matcher.group() : "N/A";
    }

    private List<String> skillExtractor(String text) {
        // naive split by spaces, filter known skills
        List<String> skills = new ArrayList<>();
        String lower = text.toLowerCase();
        for (String skill : Arrays.asList("java", "spring boot", "mysql", "docker", "kubernetes", "aws", "linux")) {
            if (lower.contains(skill.toLowerCase())) {
                skills.add(skill);
            }
        }
        return skills;
    }

    private List<String> findMatches(List<String> resumeSkills, List<String> jdSkills) {
        List<String> matches = new ArrayList<>();
        for (String skill : jdSkills) {
            if (resumeSkills.contains(skill)) {
                matches.add(skill);
            }
        }
        return matches;
    }

    private List<String> findMissing(List<String> jdSkills, List<String> resumeSkills) {
        List<String> missing = new ArrayList<>();
        for (String skill : jdSkills) {
            if (!resumeSkills.contains(skill)) {
                missing.add(skill);
            }
        }
        return missing;
    }

    private List<String> generateQuestions(List<String> missing) {
        List<String> questions = new ArrayList<>();
        if (missing.isEmpty()) {
            questions.add("Tell us about your most challenging project.");
        } else {
            for (String skill : missing) {
                questions.add("Can you describe your experience with " + skill + "?");
            }
        }
        return questions;
    }
}

