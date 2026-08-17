package com.martin.password_analyzer.service;

import com.martin.password_analyzer.dto.PasswordAnalysisResponse;
import com.martin.password_analyzer.dto.PasswordComparisonResponse;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordAnalysisService {

    private static final List<String> COMMON_PATTERNS = List.of(
        "123456", "password", "qwerty", "abcdef", "letmein",
        "111111", "12345678", "1234567890", "admin", "welcome"
    );

    private static final Pattern SEQUENTIAL = Pattern.compile(
        "(0123|1234|2345|3456|4567|5678|6789|abcd|bcde|cdef|qwer|asdf|zxcv)"
    );

    public PasswordAnalysisResponse analyze(String password) {
        PasswordAnalysisResponse response = new PasswordAnalysisResponse();

        response.setLength(password.length());

        int lower = 0, upper = 0, digits = 0, symbols = 0;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) lower++;
            else if (Character.isUpperCase(c)) upper++;
            else if (Character.isDigit(c)) digits++;
            else symbols++;
        }
        response.setLowercaseCount(lower);
        response.setUppercaseCount(upper);
        response.setDigitCount(digits);
        response.setSymbolCount(symbols);

        response.setEntropy(calculateEntropy(password, lower, upper, digits, symbols));
        response.setHasRepeatedChars(hasRepeatedChars(password));
        response.setDetectedPatterns(detectPatterns(password));

        String level = calculateStrengthLevel(response);
        response.setStrengthLevel(level);
        response.setSuggestions(generateSuggestions(response));

        return response;
    }

    private double calculateEntropy(String password, int lower, int upper, int digits, int symbols) {
        int poolSize = 0;
        if (lower > 0) poolSize += 26;
        if (upper > 0) poolSize += 26;
        if (digits > 0) poolSize += 10;
        if (symbols > 0) poolSize += 32; // aproximación de símbolos imprimibles comunes

        if (poolSize == 0) return 0.0;
        return password.length() * (Math.log(poolSize) / Math.log(2));
    }

    private boolean hasRepeatedChars(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) == password.charAt(i + 1)
                && password.charAt(i) == password.charAt(i + 2)) {
                return true; // 3+ caracteres iguales seguidos, ej. "aaa"
            }
        }
        return false;
    }

    private List<String> detectPatterns(String password) {
        List<String> found = new ArrayList<>();
        String lowerPassword = password.toLowerCase();

        for (String common : COMMON_PATTERNS) {
            if (lowerPassword.contains(common)) {
                found.add("Contiene patrón común: \"" + common + "\"");
            }
        }

        if (SEQUENTIAL.matcher(lowerPassword).find()) {
            found.add("Contiene secuencia predecible (ej. 1234, abcd, qwer)");
        }

        return found;
    }

    private String calculateStrengthLevel(PasswordAnalysisResponse r) {
        double entropy = r.getEntropy();
        boolean hasPatterns = !r.getDetectedPatterns().isEmpty();

        if (hasPatterns || entropy < 28) return "WEAK";
        if (entropy < 45) return "MEDIUM";
        if (entropy < 60) return "STRONG";
        return "VERY_STRONG";
    }

    private List<String> generateSuggestions(PasswordAnalysisResponse r) {
        List<String> suggestions = new ArrayList<>();

        if (r.getLength() < 12) suggestions.add("Usa al menos 12 caracteres");
        if (r.getUppercaseCount() == 0) suggestions.add("Añade letras mayúsculas");
        if (r.getLowercaseCount() == 0) suggestions.add("Añade letras minúsculas");
        if (r.getDigitCount() == 0) suggestions.add("Añade números");
        if (r.getSymbolCount() == 0) suggestions.add("Añade símbolos (!@#$...)");
        if (r.isHasRepeatedChars()) suggestions.add("Evita repetir el mismo carácter varias veces seguidas");
        if (!r.getDetectedPatterns().isEmpty()) suggestions.add("Evita palabras o secuencias comunes y predecibles");

        return suggestions;
    }

    public PasswordComparisonResponse compare(String passwordA, String passwordB) {
        PasswordComparisonResponse response = new PasswordComparisonResponse();

        PasswordAnalysisResponse analysisA = analyze(passwordA);
        PasswordAnalysisResponse analysisB = analyze(passwordB);

        response.setAnalysisA(analysisA);
        response.setAnalysisB(analysisB);

        if (analysisA.getEntropy() > analysisB.getEntropy()) {
            response.setStrongerPassword("A");
        } else if (analysisB.getEntropy() > analysisA.getEntropy()) {
            response.setStrongerPassword("B");
        } else {
            response.setStrongerPassword("TIE");
        }

        return response;
    }
}