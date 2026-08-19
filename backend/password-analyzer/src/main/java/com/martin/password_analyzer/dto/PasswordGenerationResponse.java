package com.martin.password_analyzer.dto;

public class PasswordGenerationResponse {

    private String password;
    private PasswordAnalysisResponse analysis;

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public PasswordAnalysisResponse getAnalysis() { return analysis; }
    public void setAnalysis(PasswordAnalysisResponse analysis) { this.analysis = analysis; }
}