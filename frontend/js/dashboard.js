const API_BASE_URL = "/api";

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("resumeForm");

  if (!form) {
    console.error("resumeForm not found in DOM");
    return;
  }

  form.addEventListener("submit", async (e) => {
    e.preventDefault();
    await analyzeResume();
  });
});

async function analyzeResume() {
  const fileInput = document.getElementById("resumeFile");
  const jobDescription = document.getElementById("jobDescription");

  const file = fileInput?.files?.[0];
  const jobText = jobDescription?.value;

  const token = localStorage.getItem("token");

  // ✅ HARD CHECKS (prevents silent 403)
  if (!token) {
    alert("You are not logged in (token missing). Please login again.");
    return;
  }

  if (!file || !jobText) {
    alert("Please upload a resume and enter a job description.");
    return;
  }

  const formData = new FormData();
  formData.append("file", file);
  formData.append("jobDescription", jobText);

  try {
    const response = await fetch(`${API_BASE_URL}/resume/analyze`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${token}`
      },
      body: formData
    });

    // ✅ IMPORTANT: show backend error message instead of hiding it
    if (!response.ok) {
      const errorText = await response.text();
      console.error("Backend Error:", errorText);
      throw new Error(errorText || "Analysis failed");
    }

    const result = await response.json();

    document.getElementById("result").innerHTML = `
      <h4>Candidate</h4>
      <p><strong>Name:</strong> ${result.candidateName || "N/A"}</p>
      <p><strong>Email:</strong> ${result.candidateEmail || "N/A"}</p>
      <p><strong>Phone:</strong> ${result.candidatePhone || "N/A"}</p>

      <h4>Analysis</h4>
      <p><strong>Match Score:</strong> ${result.matchScore ?? 0}%</p>
      <p><strong>Matching Skills:</strong> ${(result.matchingSkills || []).join(", ") || "None"}</p>
      <p><strong>Missing Skills:</strong> ${(result.missingSkills || []).join(", ") || "None"}</p>

      <h4>Interview Questions</h4>
      <ul>
        ${(result.interviewQuestions || [])
          .map(q => `<li>${q}</li>`)
          .join("") || "<li>No questions generated</li>"}
      </ul>
    `;

  } catch (err) {
    console.error("Frontend Error:", err);
    alert("Request failed: " + err.message);
  }
}
