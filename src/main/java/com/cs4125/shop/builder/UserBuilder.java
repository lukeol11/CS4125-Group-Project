package com.cs4125.shop.builder;

import com.cs4125.shop.model.User;

public class UserBuilder {
    private String email;
    private String rawPassword;
    private int loyaltyPoints;

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
        return this;
    }

    public UserBuilder withLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
        return this;
    }

    public User build() {
        return new User(email, rawPassword, loyaltyPoints);
    }
}
