package com.cs4125.shop.model;

public class ThresholdDiscount implements Discount {
    private double thresholdAmount;
    private double discountPercentage;

    public ThresholdDiscount(double thresholdAmount, double discountPercentage) {
        this.thresholdAmount = thresholdAmount;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        // Apply discount if the total amount exceeds the threshold
        if (originalPrice > thresholdAmount) {
            return originalPrice * (1 - discountPercentage / 100);
        }
        return originalPrice;
    }

    @Override
    public String getDescription() {
        return "Threshold Discount: " + discountPercentage + "% off on orders over " + thresholdAmount;
    }

    @Override
    public boolean isApplicable() {
        return true; // You might want to refine the condition based on your business rules
    }
}
