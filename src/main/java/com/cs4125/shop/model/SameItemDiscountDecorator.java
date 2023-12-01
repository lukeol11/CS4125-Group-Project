package com.cs4125.shop.model;

import com.cs4125.shop.shoppingcart.ShoppingCart;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SameItemDiscountDecorator extends DiscountDecorator {
    private ShoppingCart shoppingCart; // Change to ShoppingCart

    public SameItemDiscountDecorator(Discount decoratedDiscount, ShoppingCart shoppingCart) {
        super(decoratedDiscount);
        this.shoppingCart = shoppingCart;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        // Additional behavior specific to this decorator
        return super.applyDiscount(originalPrice * 0.9); // Apply a 10% discount
    }

    @Override
    public String getDescription() {
        // Additional behavior specific to this decorator
        return "Same Item Discount: 10% off on items with the same name";
    }

    @Override
    public boolean isApplicable() {
        // Additional behavior specific to this decorator
        List<Component> cartItems = shoppingCart.getComponents();
        Set<String> itemNames = new HashSet<>();

        for (Component item : cartItems) {
            String itemName = item.getName();
            if (itemNames.contains(itemName)) {
                return true; // Apply discount if the same item name is found
            } else {
                itemNames.add(itemName);
            }
        }

        return false;
    }
}
