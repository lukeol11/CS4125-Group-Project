package com.cs4125.shop.validation;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidationRule {
    public boolean validatePassword(String password) {
        // Password must be between 8 and 16 characters,
        // contain at least one uppercase letter, one lowercase letter,
        // one digit, and one special character.
        if (!password.matches("(?i)^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,16}$")) {
            return false; // Password is invalid.
        }

        return true; // Password is valid.
    }
}
