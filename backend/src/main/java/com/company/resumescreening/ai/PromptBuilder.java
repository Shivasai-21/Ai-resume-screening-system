package com.company.resumescreening.ai;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildPrompt(String resumeText,
                              String jobDescription) {

        return """
You are an ATS Expert and Senior Technical Recruiter.

Extract candidate details from the resume.

Return ONLY VALID JSON.

Do not return explanations.
Do not return markdown.
Do not return text before or after JSON.

Return exactly this structure:

{
  "candidateName":"",
  "candidateEmail":"",
  "candidatePhone":"",
  "matchScore":0,
  "matchingSkills":[],
  "missingSkills":[],
  "strengths":[],
  "weaknesses":[],
  "recommendation":"",
  "interviewQuestions":[]
}

Resume:
%s

Job Description:
%s
""".formatted(resumeText, jobDescription);
    }
}
