const API_BASE_URL = "/api";

async function analyzeResume() {

  const file = document.getElementById("resumeFile").files[0];
  const jobDescription = document.getElementById("jobDescription").value;

  const loading = document.getElementById("loading");
  const result = document.getElementById("result");

  if (!file || !jobDescription) {
    alert("Please upload resume and job description");
    return;
  }

  loading.classList.remove("hidden");
  result.classList.add("hidden");

  const formData = new FormData();
  formData.append("file", file);
  formData.append("jobDescription", jobDescription);

  try {
    const response = await fetch(`${API_BASE_URL}/resume/analyze`, {
      method: "POST",
      body: formData
    });

    const data = await response.json();

    loading.classList.add("hidden");
    result.classList.remove("hidden");

    // BEAUTIFUL OUTPUT
    result.innerHTML = `
      <h2>AI Analysis Result</h2>
      <p><b>Match Score:</b> ${data.matchScore || "N/A"}%</p>
      <p><b>Skills:</b> ${data.skills || "N/A"}</p>
      <p><b>Missing Skills:</b> ${data.missingSkills || "N/A"}</p>
      <p><b>Recommendation:</b> ${data.recommendation || "N/A"}</p>
    `;

  } catch (err) {
    loading.classList.add("hidden");
    alert("Error calling backend API");
    console.error(err);
  }
}
