import { uploadResume, submitJobDescription, getInterviewQuestions } from "./api.js";

document.getElementById("resumeForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const file = document.getElementById("resumeFile").files[0];
  const jobDesc = document.getElementById("jobDescription").value;

  try {
    // Step 1: Upload resume
    const resumeResult = await uploadResume(file);

    // Step 2: Compare with job description
    const compareResult = await submitJobDescription(jobDesc);

    // Step 3: Show match score and skills
    document.getElementById("matchScore").innerText = `Match Score: ${compareResult.score}`;
    document.getElementById("matchingSkills").innerText = `Matching Skills: ${compareResult.matchingSkills.join(", ")}`;
    document.getElementById("missingSkills").innerText = `Missing Skills: ${compareResult.missingSkills.join(", ")}`;

    // Step 4: Generate interview questions
    const questions = await getInterviewQuestions(resumeResult.candidateId);
    document.getElementById("questions").innerHTML = questions.map(q => `<li>${q}</li>`).join("");

  } catch (err) {
    console.error("Error:", err);
    alert("Something went wrong. Please try again.");
  }
});

