package com.cs4125.shop.model.factory;

import com.cs4125.shop.model.Subscription;
import com.cs4125.shop.model.SubscriptionState;
import com.cs4125.shop.model.User;

public interface UserFactory {
    public default User createUser(String username, int loyaltyPoints, Subscription subscription) {
        Subscription defaultSubscription = new Subscription("Default Subscription", 0.0, 0, SubscriptionState.STANDARD);
        if (subscription == null) {
            return new User(username, loyaltyPoints, defaultSubscription);
        } else {
            return new User(username, loyaltyPoints, subscription);
        }
    }
}
