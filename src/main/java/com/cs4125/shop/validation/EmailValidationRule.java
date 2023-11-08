package com.cs4125.shop.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs4125.shop.service.UserCacheService;

@Component
public class EmailValidationRule {

    @Autowired
    private UserCacheService userCacheService;

    // Regular expression for a basic email format
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public boolean validateEmail(String email) {
        // Check if the email follows a basic format
        if (!Pattern.matches(EMAIL_REGEX, email)) {
            return false; // Email format is invalid
        }

        // Check if the email is already registered
        if (userCacheService.getUserByEmail(email) != null) {
            return false; // Email is already registered
        }

        return true; // Email is valid
    }
}
