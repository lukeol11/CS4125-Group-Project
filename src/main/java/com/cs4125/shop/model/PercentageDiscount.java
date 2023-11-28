package com.cs4125.shop.model;

public class PercentageDiscount implements Discount {
    private double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        // Apply a percentage discount
        double percentageDiscount = totalAmount * (percentage / 100);
        return totalAmount - percentageDiscount;
    }
}
