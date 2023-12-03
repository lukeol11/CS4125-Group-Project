package com.cs4125.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs4125.shop.model.User;

@Service
public class RealLoginService implements LoginService {

    @Autowired
    private UserCacheService userCacheService;

    @Override
    public User login(String email, String password) {
        User user = userCacheService.getUserByEmail(email);
        System.out.println(user);

        if (user != null && password.equals(user.getRawPassword())) {
            return user; // Successful login, return the user.
        }

        return null;
    }
}
