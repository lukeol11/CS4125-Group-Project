package com.cs4125.shop.model;

public abstract class DiscountDecorator implements Discount {
    protected Discount decoratedDiscount;

    public DiscountDecorator(Discount decoratedDiscount) {
        this.decoratedDiscount = decoratedDiscount;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return decoratedDiscount.applyDiscount(totalAmount);
    }
}