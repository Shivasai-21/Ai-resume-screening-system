// frontend/js/login.js

const API_BASE_URL = "/api"; // Nginx proxies /api to backend

document.getElementById("loginForm")
  .addEventListener("submit", async function (e) {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
      const response = await fetch(`${API_BASE_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
      });

      if (!response.ok) {
        throw new Error("Login failed");
      }

      const data = await response.json();

      // Save token in localStorage
      localStorage.setItem("token", data.token);

      // ✅ Redirect to dashboard after successful login
      window.location.href = "/dashboard.html";
    } catch (err) {
      alert("Invalid credentials. Please try again.");
      console.error("Login error:", err);
    }
  });

