package com.cs4125.shop.model;

public class BaseDiscount implements Discount {
    @Override
    public double applyDiscount(double originalPrice) {
        return originalPrice;
    }

    @Override
    public String getDescription() {
        return "No Discount Applied";
    }

    @Override
    public boolean isApplicable() {
        return true;
    }
}
