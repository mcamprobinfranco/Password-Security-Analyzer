package com.martin.password_analyzer.controller;

import com.martin.password_analyzer.dto.PasswordAnalysisResponse;
import com.martin.password_analyzer.dto.PasswordComparisonRequest;
import com.martin.password_analyzer.dto.PasswordComparisonResponse;
import com.martin.password_analyzer.dto.PasswordRequest;
import com.martin.password_analyzer.service.PasswordAnalysisService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passwords")
public class PasswordController {

    private final PasswordAnalysisService analysisService;

    public PasswordController(PasswordAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @PostMapping("/analyze")
    public PasswordAnalysisResponse analyze(@Valid @RequestBody PasswordRequest request) {
        return analysisService.analyze(request.getPassword());
    }

    @PostMapping("/compare")
    public PasswordComparisonResponse compare(@Valid @RequestBody PasswordComparisonRequest request) {
    return analysisService.compare(request.getPasswordA(), request.getPasswordB());
}

}