package com.cs4125.shop.model;

public class User {
    private String username;
    private String email;
    private String hashedPassword;
    private int loyaltyPoints;

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

    public User(String username, int loyaltyPoints) {
        this.username = username;
        this.loyaltyPoints = loyaltyPoints;
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
        loyaltyPoints += points;
    }
}
