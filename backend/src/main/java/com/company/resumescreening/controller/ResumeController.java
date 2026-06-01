package com.company.resumescreening.controller;

import com.company.resumescreening.dto.response.AIResponse;
import com.company.resumescreening.service.AIService;
import com.company.resumescreening.service.PdfService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final AIService aiService;
    private final PdfService pdfService;

    public ResumeController(AIService aiService, PdfService pdfService) {
        this.aiService = aiService;
        this.pdfService = pdfService;
    }

    @PostMapping("/analyze")
    public AIResponse analyze(@RequestParam("file") MultipartFile file,
                              @RequestParam("jobDescription") String jobDescription) {

        // Extract text from resume
        String resumeText = pdfService.extractText(file);

        // Analyze resume against job description
        return aiService.analyze(resumeText, jobDescription);
    }
}

