package com.cs4125.shop.shoppingcart;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.controller.*;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Component> components;
    private CompatibilityChecker checker;

    public ShoppingCart() {
        components = new ArrayList<>();
        checker = new CompatibilityChecker();
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

    public boolean checkCompatibility(List<Component> items, Component component) {
        return checker.checkCompatibility(items, component);
    }

    public boolean isEmpty() {
        if (components == null) {
            return true;
        }
        return false;
    }
}
