document.getElementById("resumeForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const fileInput = document.getElementById("resumeFile");
  const jobDescription = document.getElementById("jobDescription").value;
  const loading = document.getElementById("loading");
  const resultBox = document.getElementById("result");

  // Show loading spinner
  loading.classList.remove("hidden");
  resultBox.classList.add("hidden");

  if (!fileInput.files.length) {
    alert("Please upload a resume file.");
    loading.classList.add("hidden");
    return;
  }

  const file = fileInput.files[0];
  const formData = new FormData();
  formData.append("file", file);
  formData.append("jobDescription", jobDescription);

  try {
    const response = await fetch("/api/resume/analyze", {
      method: "POST",
      headers: {
        "Authorization": "Bearer " + localStorage.getItem("token")
      },
      body: formData
    });

    if (response.ok) {
      const result = await response.json();

      // Update UI
      document.getElementById("scoreCircle").innerText = (result.matchScore || 0) + "%";
      document.getElementById("skillsBox").innerText = result.skillsAnalysis || "No skills found";
      document.getElementById("recommendation").innerText = result.recommendation || "No recommendation available";

      loading.classList.add("hidden");
      resultBox.classList.remove("hidden");
    } else {
      alert("Analysis failed");
      loading.classList.add("hidden");
    }
  } catch (err) {
    console.error("Error analyzing resume:", err);
    alert("Failed to analyze resume. Check console for details.");
    loading.classList.add("hidden");
  }
});

