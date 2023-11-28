package com.cs4125.shop.model;

public class LoyaltyPointsDiscount implements Discount {
    private int loyaltyPoints;

    public LoyaltyPointsDiscount(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        // Apply a discount based on loyalty points
        double loyaltyDiscount = loyaltyPoints * 0.01;
        return totalAmount - loyaltyDiscount;
    }
}