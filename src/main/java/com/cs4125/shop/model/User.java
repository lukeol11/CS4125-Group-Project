package com.cs4125.shop.model;

import com.cs4125.shop.model.factory.UserFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

public class User implements UserFactory, Serializable {
    private String username;
    private String email;
    private String hashedPassword;
    private String rawPassword;
    private int loyaltyPoints;

    public User() {
    }

    // Constructors
    public User(String email, String rawPassword, int loyaltyPoints) {
        this.email = email;
        this.hashedPassword = encryptPassword(rawPassword); // Encrypting the password
        this.rawPassword = rawPassword; // Storing the raw password temporarily
        this.loyaltyPoints = loyaltyPoints;
    }

    public User(String username, String email, String rawPassword, int loyaltyPoints) {
        this.username = username;
        this.email = email;
        this.hashedPassword = encryptPassword(rawPassword); // Encrypting the password
        this.rawPassword = rawPassword; // Storing the raw password temporarily
        this.loyaltyPoints = loyaltyPoints;
    }

    public User(String username, int loyaltyPoints) {
        this.username = username;
        this.loyaltyPoints = loyaltyPoints;
    }

    // Getter and setter methods
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

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
        this.hashedPassword = encryptPassword(rawPassword); // Encrypting the password when setting raw password.
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

    private String encryptPassword(String rawPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }
}
