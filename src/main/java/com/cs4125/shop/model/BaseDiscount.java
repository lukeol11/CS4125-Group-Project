package com.cs4125.shop.model;

public class BaseDiscount implements Discount {
    @Override
    public double applyDiscount(double originalPrice) {
        // Base discount doesn't apply any discount
        return originalPrice;
    }

    @Override
    public String getDescription() {
        return "No Discount Applied";
    }

    @Override
    public boolean isApplicable() {
        return true; // Always applicable for the base discount
    }
}
