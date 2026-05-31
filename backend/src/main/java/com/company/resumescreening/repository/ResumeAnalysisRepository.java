package com.company.resumescreening.repository;

import com.company.resumescreening.entity.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeAnalysisRepository
        extends JpaRepository<ResumeAnalysis, Long> {
}
