package com.company.resumescreening.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resume_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer matchScore;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String matchingSkills;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String missingSkills;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String summary;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String interviewQuestions;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
