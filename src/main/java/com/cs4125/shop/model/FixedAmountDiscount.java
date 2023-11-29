package com.cs4125.shop.model;

public class FixedAmountDiscount implements Discount {
    private double amount;

    public FixedAmountDiscount(double amount) {
        this.amount = amount;
    }

    private boolean applicable;

    public FixedAmountDiscount(double amount, boolean applicable) {
        this.amount = amount;
        this.applicable = applicable;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return applicable ? originalPrice - amount : originalPrice;
    }

    @Override
    public boolean isApplicable() {
        return applicable;
    }

    @Override
    public String getDescription() {
        return "Fixed Amount Discount: " + amount;
    }
}