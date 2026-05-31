document.getElementById("resumeForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const file = document.getElementById("resumeFile").files[0];
  const jobDescription = document.getElementById("jobDescription").value;

  const formData = new FormData();
  formData.append("file", file);
  formData.append("jobDescription", jobDescription);

  const response = await fetch("/api/resume/analyze", {
    method: "POST",
    headers: {
      "Authorization": "Bearer " + localStorage.getItem("token")
    },
    body: formData
  });

  if (response.ok) {
    const result = await response.json();
    document.getElementById("result").innerText = JSON.stringify(result, null, 2);
  } else {
    alert("Analysis failed");
  }
});

