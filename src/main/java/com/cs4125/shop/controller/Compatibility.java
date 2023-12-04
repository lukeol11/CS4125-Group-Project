package com.cs4125.shop.controller;

import java.util.List;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.shoppingcart.ShoppingCart;

public class Compatibility {

    public Compatibility() {
    }

    public boolean isCompatibleWith(List<Component> items, ShoppingCart cart) {
        System.out.println("Run Compatibility");
        for (Component cartComponent : cart.getComponents()) {
            boolean isComponentCompatible = false;
            if (cartComponent.isCompatibleWith(items)) {
                isComponentCompatible = true;
            }
            if (!isComponentCompatible) {
                return false;
            }
        }
        return true; // compatible
    }
}
