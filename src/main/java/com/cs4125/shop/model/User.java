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

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += loyaltyPoints;
    }

    public void deductLoyaltyPoints(double discount) {
        if (discount >= 0 && discount <= loyaltyPoints) {
            loyaltyPoints -= discount;
        } else {
            throw new IllegalArgumentException("Invalid discount amount.");
        }
    }

    public int getLoyaltyPoints() {
        if (subscription != null) {
            return subscription.calculateLoyaltyPoints(loyaltyPoints);
        } else {
            return loyaltyPoints; // No subscription, use points
        }
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void addSubscription(Subscription subscriptionToAdd) {
        if (subscription == null) {
            this.subscription = subscriptionToAdd;
            System.out.println("Subscription added to the cart: " + subscriptionToAdd.getName());
        } else {
            if (subscription.getName().equals(subscriptionToAdd.getName())) {
                System.out.println("This subscription is already in the cart.");
            } else {
                System.out.println("Another subscription already exists in the cart.");
            }
        }
    }
}
