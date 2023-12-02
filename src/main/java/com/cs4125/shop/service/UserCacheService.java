package com.cs4125.shop.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cs4125.shop.model.User;

@Service
public class UserCacheService {
    private Map<String, User> userCache = new HashMap<>();

    public void registerUser(User user) {
        userCache.put(user.getEmail(), user);
    }

    public User getUserByEmail(String email) {
        return userCache.get(email);
    }
}