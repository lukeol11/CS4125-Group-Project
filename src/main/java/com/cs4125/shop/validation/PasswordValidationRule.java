package com.cs4125.shop.validation;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidationRule {
    public boolean validatePassword(String password) {
        // Check if the password meets the length requirement (between 8 and 16 characters).
        if (password.length() < 8 || password.length() > 16) {
            return false; // Password length is invalid.
        }

        // Check if the password contains at least one uppercase letter.
        if (!password.matches(".*[A-Z].*")) {
            return false; // Password does not contain an uppercase letter.
        }

        // Check if the password contains at least one lowercase letter.
        if (!password.matches(".*[a-z].*")) {
            return false; // Password does not contain a lowercase letter.
        }

        // Check if the password contains at least one digit.
        if (!password.matches(".*\\d.*")) {
            return false; // Password does not contain a digit.
        }

        // Check if the password contains at least one special character.
        if (!password.matches(".*[!@#$%^&*()].*")) {
            return false; // Password does not contain a special character.
        }

        return true; // Password is valid.
    }
}