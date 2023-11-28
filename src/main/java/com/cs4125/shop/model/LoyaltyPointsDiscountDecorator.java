package com.cs4125.shop.model;

public class LoyaltyPointsDiscountDecorator extends DiscountDecorator {
    private int additionalPoints;

    public LoyaltyPointsDiscountDecorator(Discount decoratedDiscount, int additionalPoints) {
        super(decoratedDiscount);
        this.additionalPoints = additionalPoints;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        // Apply loyalty points discount and additional points discount
        double loyaltyDiscount = decoratedDiscount.applyDiscount(totalAmount -
                (additionalPoints * 0.01));
        return loyaltyDiscount;
    }
}
