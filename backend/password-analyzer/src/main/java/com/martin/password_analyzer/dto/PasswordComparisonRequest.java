package com.martin.password_analyzer.dto;

import jakarta.validation.constraints.NotBlank;

public class PasswordComparisonRequest {

    @NotBlank(message = "La primera contraseña no puede estar vacía")
    private String passwordA;

    @NotBlank(message = "La segunda contraseña no puede estar vacía")
    private String passwordB;

    public String getPasswordA() { return passwordA; }
    public void setPasswordA(String passwordA) { this.passwordA = passwordA; }

    public String getPasswordB() { return passwordB; }
    public void setPasswordB(String passwordB) { this.passwordB = passwordB; }
}