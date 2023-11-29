package com.cs4125.shop.model;

public interface Discount {
<<<<<<< HEAD
    double applyDiscount(double totalAmount);
}
=======
    double applyDiscount(double originalPrice);

    boolean isApplicable();

    String getDescription();
}
>>>>>>> d5a9878 (added discounts)
