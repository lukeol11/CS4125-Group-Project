package com.cs4125.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cs4125.shop.model.User;

@Service
public class LoginService {

    @Autowired
    private UserCacheService userCacheService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(String email, String password) {
        User user = userCacheService.getUserByEmail(email);

        // Check if the user with the given email exists and if the password matches
        if (user != null && passwordEncoder.matches(password, user.getHashedPassword())) {
            return user; // Successful login, return the user
        }

        return null; // Invalid email or password, login failed
    }
}