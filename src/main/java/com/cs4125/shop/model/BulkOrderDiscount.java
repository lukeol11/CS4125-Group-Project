package com.cs4125.shop.model;

import com.cs4125.shop.services.CartTotal;

public class BulkOrderDiscount implements Discount {

    private boolean applicable;
    private double cartThreshold;
    private CartTotal cartTotal;

    public BulkOrderDiscount(boolean applicable, double cartThreshold, CartTotal cartTotal) {
        this.applicable = applicable;
        this.cartThreshold = cartThreshold;
        this.cartTotal = cartTotal;
    }

    @Override
    public double applyDiscount(double discountedAmount) {
        double totalCartValue = cartTotal.calculateTotalCartPrice();

        // Check if applicable and cart value exceeds the threshold
        if (applicable && totalCartValue > cartThreshold) {
            return discountedAmount * 0.75; // Apply 25% discount
        } else {
            return discountedAmount; // No discount
        }
    }

    @Override
    public boolean isApplicable() {
        double totalCartValue = cartTotal.calculateTotalCartPrice();
        return applicable && totalCartValue > cartThreshold;
    }

    @Override
    public String getDescription() {
        return "25% off on bulk orders exceeding " + cartThreshold + " value";
    }
}
