package com.cs4125.shop.model;

import com.cs4125.shop.services.CartTotal;

public class ThresholdDiscountDecorator extends DiscountDecorator {
    private double thresholdAmount;
    private double discountPercentage;
    private CartTotal cartTotal; // Add CartTotal as a member variable

    public ThresholdDiscountDecorator(Discount decoratedDiscount, double thresholdAmount, double discountPercentage,
            CartTotal cartTotal) {
        super(decoratedDiscount);
        this.thresholdAmount = thresholdAmount;
        this.discountPercentage = discountPercentage;
        this.cartTotal = cartTotal;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        // Additional behavior specific to this decorator
        return super.applyDiscount(originalPrice * (1 - discountPercentage / 100));
    }

    @Override
    public String getDescription() {
        // Additional behavior specific to this decorator
        return "Threshold Discount: " + discountPercentage + "% off on orders over " + thresholdAmount;
    }

    @Override
    public boolean isApplicable() {
        // Additional behavior specific to this decorator
        double totalCartPrice = cartTotal.calculateTotalCartPrice();
        return super.isApplicable() && decoratedDiscount.isApplicable() && totalCartPrice > thresholdAmount;
    }
}
