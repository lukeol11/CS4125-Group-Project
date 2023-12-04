package com.cs4125.shop.model.factory;

import com.cs4125.shop.model.User;

public interface UserFactory {
    public default User registerUser(String email, String rawPassword, int loyaltyPoints) {
        return new User(email, rawPassword, loyaltyPoints);
    }
}