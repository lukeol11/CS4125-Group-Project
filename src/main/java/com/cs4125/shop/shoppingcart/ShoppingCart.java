package com.cs4125.shop.shoppingcart;

import com.cs4125.shop.model.Component;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Component> components;

    public ShoppingCart() {
        components = new ArrayList<>();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component) {
        components.remove(component);
    }

    public List<Component> getComponents() {
        return components;
    }

    public void clearCart() {
        components.clear();
    }

}
