const API_BASE_URL = "/api";

function getToken() {
  return localStorage.getItem("token");
}

function authHeaders() {
  return {
    "Content-Type": "application/json",
    "Authorization": "Bearer " + getToken()
  };
}

async function apiPost(url, data) {
  return fetch(API_BASE_URL + url, {
    method: "POST",
    headers: authHeaders(),
    body: JSON.stringify(data)
  });
}
