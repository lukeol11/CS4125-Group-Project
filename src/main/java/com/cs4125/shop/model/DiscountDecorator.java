package com.cs4125.shop.model;

public abstract class DiscountDecorator implements Discount {
    protected Discount decoratedDiscount;

    public DiscountDecorator(Discount decoratedDiscount) {
        this.decoratedDiscount = decoratedDiscount;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return decoratedDiscount.applyDiscount(originalPrice);
    }

    @Override
    public String getDescription() {
        return decoratedDiscount.getDescription();
    }

    @Override
    public boolean isApplicable() {
        return decoratedDiscount.isApplicable();
    }
}
