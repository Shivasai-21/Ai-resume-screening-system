package com.company.resumescreening.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIResponse {

    private Integer matchScore;
    private String matchingSkills;
    private String missingSkills;
    private String summary;
    private String recommendation;

}
