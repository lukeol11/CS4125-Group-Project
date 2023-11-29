package com.cs4125.shop.model.factory;

import com.cs4125.shop.model.User.User;

public interface UserFactory {
    public default User createUser(String username, int loyaltyPoints) {
        return new User(username, loyaltyPoints);
    }
}