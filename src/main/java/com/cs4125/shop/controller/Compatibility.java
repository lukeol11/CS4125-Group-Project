package com.cs4125.shop.controller;

import java.util.List;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.shoppingcart.ShoppingCart;

// Define the interface for the CompatibilityState
interface CompatibilityState {
    boolean isCompatibleWith(Component component);
    boolean isCompatibleWith(List<Component> items, ShoppingCart cart);
}

// Define specific states implementing the CompatibilityState interface
class CompatibleState implements CompatibilityState {
    public boolean isCompatibleWith(List<Component> items, ShoppingCart cart) {
        return true;
    }

    public boolean isCompatibleWith(Component component) {
        throw new UnsupportedOperationException("Unimplemented method 'isCompatibleWith'");
    }
}

class NotCompatibleState implements CompatibilityState {
    public boolean isCompatibleWith(List<Component> items, ShoppingCart cart) {
        return false;
    }

    public boolean isCompatibleWith(Component component) {
        throw new UnsupportedOperationException("Unimplemented method 'isCompatibleWith'");
    }
}

// Compatibility class using the State pattern
class CompatibilityChecker {
    private CompatibilityState currentState;

    public CompatibilityChecker() {
        currentState = new CompatibleState(); // Set initial state to compatible
    }

    public void setState(CompatibilityState state) {
        this.currentState = state;
    }

    public boolean checkCompatibility(List<Component> items, ShoppingCart cart) {
        return currentState.isCompatibleWith(items, cart);
    }
}

public class Compatibility {
    public Compatibility() {
        // CompatibilityChecker checker = new CompatibilityChecker();

        // // If there is no correlation between components then they are compatible
        // boolean noCorrelation = true;
        // if (noCorrelation) {
        //     checker.setState(new CompatibleState());
        // } else {
        //     checker.setState(new NotCompatibleState());
        // }

        // // Perform compatibility check using the set state
        // List<Component> components = /* Get components */;
        // ShoppingCart cart = /* Get the shopping cart */;

        // boolean isCompatible = checker.checkCompatibility(components, cart);
        // if (isCompatible) {
        //     System.out.println("Components are compatible.");
        // } else {
        //     System.out.println("Components are not compatible.");
        // }
    }
}
