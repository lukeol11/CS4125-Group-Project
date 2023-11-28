package com.cs4125.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RegistrationResult registerUser(String email, String password, String confirmPassword) {
        // Perform email validation.
        if (!emailValidationRule.validateEmail(email)) {
            return RegistrationResult.INVALID_EMAIL;
        }

        // Perform password validation on the raw password.
        if (!passwordValidationRule.validatePassword(password)) {
            return RegistrationResult.INVALID_PASSWORD;
        }

        // Check if the email is already registered.
        if (userCacheService.getUserByEmail(email) != null) {
            return RegistrationResult.EMAIL_ALREADY_REGISTERED;
        }

        // Hash the password before saving.
        String hashedPassword = passwordEncoder.encode(password);

        // Check if the passwords match.
        if (!passwordEncoder.matches(confirmPassword, hashedPassword)) {
            return RegistrationResult.PASSWORDS_DO_NOT_MATCH;
        }

        // Register the user.
        userCacheService.registerUser(new User(email, hashedPassword, 0));

        return RegistrationResult.SUCCESS;
    }
}

