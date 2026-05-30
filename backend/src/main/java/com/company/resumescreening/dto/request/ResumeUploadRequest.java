package com.company.resumescreening.dto.request;

import lombok.Data;

@Data
public class ResumeUploadRequest {

    private String name;
    private String email;
    private String phone;
    private String resumeText;
}
