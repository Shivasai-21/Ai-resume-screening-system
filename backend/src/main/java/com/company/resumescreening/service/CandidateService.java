package com.company.resumescreening.service;

import com.company.resumescreening.entity.Candidate;
import com.company.resumescreening.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
