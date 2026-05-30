package com.company.resumescreening.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumeResponse {

    private Long candidateId;
    private String candidateName;
    private Integer matchScore;
    private String summary;
}
