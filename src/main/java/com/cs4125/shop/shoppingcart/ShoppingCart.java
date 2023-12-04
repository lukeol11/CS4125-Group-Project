package com.cs4125.shop.shoppingcart;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.model.Subscription;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Component> components;
    private Subscription subscription;

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

    //To check if the cart contains a subscription
    public boolean hasSubscription() {
        return components.stream().anyMatch(component -> component instanceof Subscription);
    }

    //Get subscription that is in the cart
    public Subscription getSubscription() {
        return subscription;
    }
}
