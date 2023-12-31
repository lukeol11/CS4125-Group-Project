package com.cs4125.shop.model;

public interface Discount {
    double applyDiscount(double originalPrice);

    boolean isApplicable();

    String getDescription();
}
