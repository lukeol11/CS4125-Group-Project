package com.cs4125.shop.model.factory;

import com.cs4125.shop.model.Subscription;
import com.cs4125.shop.model.User;

public interface UserFactory {
    public default User createUser(String username, int loyaltyPoints, Subscription subscription) {
        return new User(username, loyaltyPoints, subscription);
    }
}