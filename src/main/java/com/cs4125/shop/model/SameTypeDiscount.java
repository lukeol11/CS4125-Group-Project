package com.cs4125.shop.model;

import com.cs4125.shop.shoppingcart.ShoppingCart;

import java.util.List;

public class SameTypeDiscount implements Discount {

    private ShoppingCart cart; // Declare ShoppingCart variable at the class level

    public SameTypeDiscount(ShoppingCart cart) {
        this.cart = cart;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        // Apply 20% discount to the original price
        return originalPrice * 0.8;
    }

    @Override
    public boolean isApplicable() {
        List<Component> components = cart.getComponents();

        // Ensure there are at least two components in the cart
        if (components.size() >= 2) {
            // Check if the names of the first and second components match
            String firstName = components.get(0).getName();
            String secondName = components.get(1).getName();
            return firstName.equals(secondName);
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "20% off on the second item if it's of the same type as the first";
    }

}
