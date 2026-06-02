[root@ip-172-31-8-50 Ai-resume-screening-system]# cat backend/src/main/java/com/company/resumescreening/ai/ResponseParser.java
package com.company.resumescreening.ai;

import com.company.resumescreening.dto.response.AIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ResponseParser {

    private final ObjectMapper mapper = new ObjectMapper();

    public AIResponse parse(String response) {

        try {

            response = response
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            AIResponse aiResponse =
                    mapper.readValue(response, AIResponse.class);

            if (aiResponse.getMatchingSkills() == null)
                aiResponse.setMatchingSkills(java.util.List.of());

            if (aiResponse.getMissingSkills() == null)
                aiResponse.setMissingSkills(java.util.List.of());

            if (aiResponse.getStrengths() == null)
                aiResponse.setStrengths(java.util.List.of());

            if (aiResponse.getWeaknesses() == null)
                aiResponse.setWeaknesses(java.util.List.of());

            if (aiResponse.getInterviewQuestions() == null)
                aiResponse.setInterviewQuestions(java.util.List.of());

            return aiResponse;

        } catch (Exception e) {

            e.printStackTrace();

            AIResponse fallback = new AIResponse();

            fallback.setCandidateName("Unknown");
            fallback.setMatchScore(0);
            fallback.setRecommendation(
                    "Failed to parse Gemini response");

            return fallback;
        }
    }
}
