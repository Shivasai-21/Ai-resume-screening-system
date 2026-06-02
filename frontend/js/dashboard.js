document.getElementById("resumeForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const fileInput = document.getElementById("resumeFile");
  const jobDescription = document.getElementById("jobDescription").value;
  const loading = document.getElementById("loading");
  const resultBox = document.getElementById("result");

  // Show loading
  loading.classList.remove("hidden");
  resultBox.classList.add("hidden");

  if (!fileInput.files.length) {
    alert("Please upload a resume file.");
    loading.classList.add("hidden");
    return;
  }

  const formData = new FormData();
  formData.append("file", fileInput.files[0]);
  formData.append("jobDescription", jobDescription);

  try {
    const response = await fetch("/api/resume/analyze", {
      method: "POST",
      headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
      },
      body: formData
    });

    if (!response.ok) {
      alert("Analysis failed");
      loading.classList.add("hidden");
      return;
    }

    const result = await response.json();
    console.log("API RESPONSE:", result);
    console.log("matchingSkills =", result.matchingSkills);
    console.log("matchedSkills =", result.matchedSkills);
    // Candidate Info
    setText("candidateName", result.candidateName);
    setText("candidateEmail", result.candidateEmail);
    setText("candidatePhone", result.candidatePhone);

    // Analysis
    setText("scoreCircle", (result.matchScore || 0) + "%");
    setText("missingSkills", arrayToText(result.missingSkills));
    // Analysis
    setText("scoreCircle", (result.matchScore || 0) + "%");
    setText("matchedSkills", arrayToText(result.matchingSkills));  // FIXED
    setText("missingSkills", arrayToText(result.missingSkills));
    setText("strengths", arrayToText(result.strengths));
    setText("weaknesses", arrayToText(result.weaknesses));

    setText("strengths", arrayToText(result.strengths));
    setText("weaknesses", arrayToText(result.weaknesses));

    // Interview Questions
    const ul = document.getElementById("interviewQuestions");
    ul.innerHTML = "";
    if (Array.isArray(result.interviewQuestions)) {
      result.interviewQuestions.forEach(q => {
        const li = document.createElement("li");
        li.innerText = q;
        ul.appendChild(li);
      });
    }

    // Recommendation
    setText("recommendation", result.recommendation);

    // Show results
    loading.classList.add("hidden");
    resultBox.classList.remove("hidden");

  } catch (err) {
    console.error("Error analyzing resume:", err);
    alert("Failed to analyze resume.");
    loading.classList.add("hidden");
  }
});

// Helpers
function setText(id, value) {
  const el = document.getElementById(id);
  if (el) el.innerText = value || "N/A";
}

function arrayToText(arr) {
  if (!arr) return "N/A";
  if (Array.isArray(arr)) return arr.join(", ");
  return arr;
}

