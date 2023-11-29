package com.cs4125.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs4125.shop.model.User;
import com.cs4125.shop.validation.EmailValidationRule;
import com.cs4125.shop.validation.PasswordValidationRule;

@Service
public class RegistrationService {

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private EmailValidationRule emailValidationRule;

    @Autowired
    private PasswordValidationRule passwordValidationRule;

    public boolean registerUser(User user, String confirmPassword) {
        // Perform email validation.
        if (!emailValidationRule.validateEmail(user.getEmail())) {
            return false; // Email validation failed.
        }

        // Perform password validation.
        if (!passwordValidationRule.validatePassword(user.getHashedPassword())) {
            return false; // Password validation failed.
        }

        // Check if the email is already registered.
        if (userCacheService.getUserByEmail(user.getEmail()) != null) {
            return false; // Email is already registered.
        }

        // Check if the passwords match.
        if (!user.getHashedPassword().equals(confirmPassword)) {
            return false; // Passwords do not match.
        }

        user.setHashedPassword(user.getHashedPassword());

        // Register the user.
        userCacheService.registerUser(user);

        return true; // Registration successful.
    }
}