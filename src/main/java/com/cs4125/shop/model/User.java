package com.cs4125.shop.model;

import com.cs4125.shop.model.factory.UserFactory;

public class User implements UserFactory {
    private String username;
    private int loyaltyPoints;

    public User(String username, int loyaltyPoints) {
        this.username = username;
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getUsername() {
        return username;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        loyaltyPoints += points;
    }

    public void deductLoyaltyPoints(double discount) {
        if (discount >= 0 && discount <= loyaltyPoints) {
            loyaltyPoints -= discount;
        } else {
            throw new IllegalArgumentException("Invalid discount amount.");
        }
    }
}
