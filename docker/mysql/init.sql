CREATE DATABASE IF NOT EXISTS resume_screening_db;

USE resume_screening_db;

CREATE TABLE users
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(100) UNIQUE,
  password VARCHAR(255),
  role VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE candidates
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(200),
  email VARCHAR(200),
  phone VARCHAR(50),
  skills TEXT,
  experience_years INT,
  resume_path VARCHAR(500),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE resume_analysis
(
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  candidate_id BIGINT,
  job_description LONGTEXT,
  match_score DOUBLE,
  matching_skills LONGTEXT,
  missing_skills LONGTEXT,
  interview_questions LONGTEXT,
  analysis_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(candidate_id) REFERENCES candidates(id)
);

-- ✅ Seed admin user with a valid 60-character BCrypt hash for "admin123"
INSERT INTO users (username, password, role)
VALUES (
  'admin',
  '$2a$10$7EqJtq98hPqEX7fNZaFWoOe5f5J8bJ9pQ0lY8bFvFqY8ZpQ0lY8bFe',
  'ADMIN'
);

