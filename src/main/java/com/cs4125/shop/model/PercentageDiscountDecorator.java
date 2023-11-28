package com.cs4125.shop.model;

public class PercentageDiscountDecorator extends DiscountDecorator {
    private double additionalDiscount;

    public PercentageDiscountDecorator(Discount decoratedDiscount, double additionalDiscount) {
        super(decoratedDiscount);
        this.additionalDiscount = additionalDiscount;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        // Apply percentage discount and additional discount
        double percentageDiscount = decoratedDiscount.applyDiscount(totalAmount);
        return percentageDiscount + additionalDiscount;
    }

}
