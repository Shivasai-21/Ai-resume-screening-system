const API_BASE = "/api"; // Nginx proxies to backend

// Upload resume (PDF)
export async function uploadResume(file) {
  const formData = new FormData();
  formData.append("resume", file);

  const response = await fetch(`${API_BASE}/resume/upload`, {
    method: "POST",
    body: formData
  });

  return response.json();
}

// Submit job description
export async function submitJobDescription(description) {
  const response = await fetch(`${API_BASE}/job/compare`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ description })
  });

  return response.json();
}

// Get interview questions
export async function getInterviewQuestions(candidateId) {
  const response = await fetch(`${API_BASE}/candidate/${candidateId}/questions`);
  return response.json();
}

