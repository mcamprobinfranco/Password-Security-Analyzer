package com.martin.password_analyzer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PasswordGenerationRequest {

    @Min(value = 4, message = "La longitud mínima es 4")
    @Max(value = 64, message = "La longitud máxima es 64")
    private int length = 16;

    private boolean includeUppercase = true;
    private boolean includeLowercase = true;
    private boolean includeNumbers = true;
    private boolean includeSymbols = true;

    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public boolean isIncludeUppercase() { return includeUppercase; }
    public void setIncludeUppercase(boolean includeUppercase) { this.includeUppercase = includeUppercase; }

    public boolean isIncludeLowercase() { return includeLowercase; }
    public void setIncludeLowercase(boolean includeLowercase) { this.includeLowercase = includeLowercase; }

    public boolean isIncludeNumbers() { return includeNumbers; }
    public void setIncludeNumbers(boolean includeNumbers) { this.includeNumbers = includeNumbers; }

    public boolean isIncludeSymbols() { return includeSymbols; }
    public void setIncludeSymbols(boolean includeSymbols) { this.includeSymbols = includeSymbols; }
}