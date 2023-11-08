package com.cs4125.shop.service;

import org.springframework.stereotype.Service;

import com.cs4125.shop.model.User;


@Service
public class LoyaltyService {
    public void awardLoyaltyPoints(User user, double totalAmount) {
        int pointsToAward = (int) (totalAmount / 10); // Award 1 point for every $10 spent, adjust as needed
        user.addLoyaltyPoints(pointsToAward); // Add the awarded points to the user's balance
        // You can save the updated user information if needed
    }
}
