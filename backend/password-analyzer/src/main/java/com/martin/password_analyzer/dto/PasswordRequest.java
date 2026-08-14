package com.martin.password_analyzer.dto;

import jakarta.validation.constraints.NotBlank;

public class PasswordRequest {

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}