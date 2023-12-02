package com.cs4125.shop.model;

import com.cs4125.shop.model.factory.UserFactory;

public class User implements UserFactory {
    private String username;
    private String email;
    private String hashedPassword;
    private int loyaltyPoints;
    private Subscription subscription;

    public User(String email, String hashedPassword, int loyaltyPoints) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.loyaltyPoints = loyaltyPoints;
    }

    public User(String username, String email, String hashedPassword, int loyaltyPoints) {
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.loyaltyPoints = loyaltyPoints;
    }

    public User(String username, int loyaltyPoints, Subscription subscription) {
        this.username = username;
        this.loyaltyPoints = loyaltyPoints;
        this.subscription = subscription;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        addLoyaltyPoints(points);
    }

    public void deductLoyaltyPoints(double discount) {
        if (discount >= 0 && discount <= loyaltyPoints) {
            loyaltyPoints -= discount;
        } else {
            throw new IllegalArgumentException("Invalid discount amount.");
        }
    }

    public void applyNewSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public int getLoyaltyPoints(int points) {
        if (subscription != null) {
            return subscription.calculateLoyaltyPoints(points);
        } else {
            return points; // No subscription, use points
        }
    }
}
