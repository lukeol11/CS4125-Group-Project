package com.cs4125.shop.model;

public class PercentageDiscount implements Discount {

    private boolean applicable;
    private double percentage;

    public PercentageDiscount(double percentage, boolean applicable) {
        this.percentage = percentage;
        this.applicable = applicable;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return applicable ? originalPrice - (originalPrice * percentage / 100) : originalPrice;
    }

    @Override
    public boolean isApplicable() {
        return applicable;
    }

    @Override
    public String getDescription() {
        return "Percentage Discount: " + percentage + "%";
    }

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

}
