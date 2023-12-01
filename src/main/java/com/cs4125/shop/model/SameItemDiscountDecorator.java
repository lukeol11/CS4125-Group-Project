package com.cs4125.shop.model;

import com.cs4125.shop.shoppingcart.ShoppingCart;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SameItemDiscountDecorator extends DiscountDecorator {
    private ShoppingCart shoppingCart;

    public SameItemDiscountDecorator(Discount decoratedDiscount, ShoppingCart shoppingCart) {
        super(decoratedDiscount);
        this.shoppingCart = shoppingCart;
    }

    @Override
    public double applyDiscount(double originalPrice) {
        return super.applyDiscount(originalPrice * 0.9);
    }

    @Override
    public String getDescription() {
        return "Same Item Discount: 10% off on items with the same name";
    }

    @Override
    public boolean isApplicable() {
        List<Component> cartItems = shoppingCart.getComponents();
        Set<String> itemNames = new HashSet<>();

        for (Component item : cartItems) {
            String itemName = item.getName();
            if (itemNames.contains(itemName)) {
                return true;
            } else {
                itemNames.add(itemName);
            }
        }

        return false;
    }
}
