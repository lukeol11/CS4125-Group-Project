package com.cs4125.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs4125.shop.model.User;

@Service
public class LoginServiceProxy implements LoginService {

    @Autowired
    private RealLoginService realLoginService;

    @Override
    public User login(String email, String password) {
        System.out.println("Login attempt for email: " + email);

        User user = realLoginService.login(email, password);

        if (user != null) {
            System.out.println("Successful login for email: " + email);
        } else {
            System.out.println("Login failed for email: " + email);
        }

        return user;
    }
}
