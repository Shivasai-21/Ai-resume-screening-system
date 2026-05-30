package com.company.resumescreening.repository;

import com.company.resumescreening.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository
        extends JpaRepository<Candidate, Long> {
}
