package com.martin.password_analyzer.dto;

import java.util.List;

public class PasswordAnalysisResponse {

    private int length;
    private int lowercaseCount;
    private int uppercaseCount;
    private int digitCount;
    private int symbolCount;
    private double entropy;
    private boolean hasRepeatedChars;
    private List<String> detectedPatterns;
    private String strengthLevel; // WEAK, MEDIUM, STRONG, VERY_STRONG
    private List<String> suggestions;

    // Constructor vacío + getters y setters (o usa Lombok @Data si lo prefieres)

    public PasswordAnalysisResponse() {}

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public int getLowercaseCount() { return lowercaseCount; }
    public void setLowercaseCount(int lowercaseCount) { this.lowercaseCount = lowercaseCount; }

    public int getUppercaseCount() { return uppercaseCount; }
    public void setUppercaseCount(int uppercaseCount) { this.uppercaseCount = uppercaseCount; }

    public int getDigitCount() { return digitCount; }
    public void setDigitCount(int digitCount) { this.digitCount = digitCount; }

    public int getSymbolCount() { return symbolCount; }
    public void setSymbolCount(int symbolCount) { this.symbolCount = symbolCount; }

    public double getEntropy() { return entropy; }
    public void setEntropy(double entropy) { this.entropy = entropy; }

    public boolean isHasRepeatedChars() { return hasRepeatedChars; }
    public void setHasRepeatedChars(boolean hasRepeatedChars) { this.hasRepeatedChars = hasRepeatedChars; }

    public List<String> getDetectedPatterns() { return detectedPatterns; }
    public void setDetectedPatterns(List<String> detectedPatterns) { this.detectedPatterns = detectedPatterns; }

    public String getStrengthLevel() { return strengthLevel; }
    public void setStrengthLevel(String strengthLevel) { this.strengthLevel = strengthLevel; }

    public List<String> getSuggestions() { return suggestions; }
    public void setSuggestions(List<String> suggestions) { this.suggestions = suggestions; }
}