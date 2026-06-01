(function () {
  const token = localStorage.getItem("token");

  // protect all internal pages
  if (!token && window.location.pathname !== "/login.html") {
    window.location.href = "/login.html";
  }
})();
