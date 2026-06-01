CREATE DATABASE IF NOT EXISTS resume_screening_db;

USE resume_screening_db;

-- =====================================================
-- USERS TABLE
-- =====================================================

CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(150),
    role VARCHAR(50) DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =====================================================
-- CANDIDATES TABLE
-- =====================================================

CREATE TABLE IF NOT EXISTS candidates (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(200) NOT NULL,
    email VARCHAR(150),
    phone VARCHAR(50),
    experience_years INT DEFAULT 0,
    current_company VARCHAR(200),
    current_designation VARCHAR(200),
    skills TEXT,
    resume_file_name VARCHAR(255),
    resume_file_path VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- JOB DESCRIPTIONS TABLE
-- =====================================================

CREATE TABLE IF NOT EXISTS job_descriptions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description LONGTEXT NOT NULL,
    required_skills TEXT,
    experience_required INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- RESUME ANALYSIS TABLE
-- =====================================================

CREATE TABLE IF NOT EXISTS resume_analysis (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    candidate_id BIGINT NOT NULL,

    job_description_id BIGINT NOT NULL,

    match_score DECIMAL(5,2),

    matching_skills LONGTEXT,

    missing_skills LONGTEXT,

    ai_summary LONGTEXT,

    interview_questions LONGTEXT,

    recommendation VARCHAR(50),

    analyzed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_analysis_candidate
        FOREIGN KEY(candidate_id)
        REFERENCES candidates(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_analysis_job
        FOREIGN KEY(job_description_id)
        REFERENCES job_descriptions(id)
        ON DELETE CASCADE
);

-- =====================================================
-- AUDIT TABLE
-- =====================================================

CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    action VARCHAR(255),

    username VARCHAR(100),

    details LONGTEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- DEFAULT ADMIN USER
-- Password: admin123
-- Replace hash later with BCrypt generated hash
-- =====================================================

-- =====================================================
-- SAMPLE JOB DESCRIPTION
-- =====================================================

INSERT INTO job_descriptions
(
title,
description,
required_skills,
experience_required
)
VALUES
(
'DevOps Engineer',

'Looking for an experienced DevOps Engineer with Kubernetes, Docker, Jenkins, AWS and Terraform experience.',

'Kubernetes,Docker,Jenkins,AWS,Terraform,GitHub Actions,Linux',

3
);
