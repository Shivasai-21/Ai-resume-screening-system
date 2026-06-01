package com.company.resumescreening.ai;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildPrompt(String resumeText, String jobDescription) {

        return """
You are an ATS Expert, Technical Recruiter and Hiring Manager.

Analyze the candidate resume against the job description.

Evaluate:

1. Skills Match
2. AWS Experience
3. Kubernetes Experience
4. Docker Experience
5. Terraform Experience
6. CI/CD Experience
7. Linux Skills
8. Cloud Architecture
9. Project Relevance
10. Experience Level

Scoring Rules:

90-100 = Excellent Match
75-89 = Strong Match
60-74 = Moderate Match
Below 60 = Weak Match

Return ONLY valid JSON.

{
  "candidateName":"",
  "matchScore":0,
  "matchingSkills":[],
  "missingSkills":[],
  "strengths":[],
  "weaknesses":[],
  "recommendation":"",
  "interviewQuestions":[]
}

RESUME:
%s

JOB DESCRIPTION:
%s
""".formatted(resumeText, jobDescription);
    }
}
