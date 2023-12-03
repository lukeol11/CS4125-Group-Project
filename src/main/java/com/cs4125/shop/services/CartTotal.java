package com.cs4125.shop.services;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.shoppingcart.ShoppingCart;

public class CartTotal {
    private ShoppingCart cart;

    public CartTotal(ShoppingCart cart) {
        this.cart = cart;
    }

    public double calculateTotalCartPrice() {
        double totalPrice = 0.0;
        for (Component item : cart.getComponents()) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }
}
