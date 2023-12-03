package com.cs4125.shop.service;

import com.cs4125.shop.model.User;

public interface LoginService {
    User login(String email, String password);
}
