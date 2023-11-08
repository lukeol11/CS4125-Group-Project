package com.cs4125.shop.controller;

public class User {
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
}
