package com.martin.password_analyzer.dto;

public class PasswordComparisonResponse {

    private PasswordAnalysisResponse analysisA;
    private PasswordAnalysisResponse analysisB;
    private String strongerPassword; // "A", "B" o "TIE"

    public PasswordAnalysisResponse getAnalysisA() { return analysisA; }
    public void setAnalysisA(PasswordAnalysisResponse analysisA) { this.analysisA = analysisA; }

    public PasswordAnalysisResponse getAnalysisB() { return analysisB; }
    public void setAnalysisB(PasswordAnalysisResponse analysisB) { this.analysisB = analysisB; }

    public String getStrongerPassword() { return strongerPassword; }
    public void setStrongerPassword(String strongerPassword) { this.strongerPassword = strongerPassword; }
}