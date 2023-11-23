package com.cs4125.shop.controller;

import java.util.List;
import com.cs4125.shop.model.Component;

// Define the interface for the CompatibilityState
interface CompatibilityState {
    boolean isCompatibleWith(List<Component> items, Component component);
}

// Define specific states implementing the CompatibilityState interface
class Compatible implements CompatibilityState {
    public boolean isCompatibleWith(List<Component> items, Component component) {
        for (Component newItem : items) {
            if (!newItem.isCompatibleWith(component)) {
                return false; // If any component is not compatible, return false
            }
        }
        return true; // All components are compatible
    }
}


class NotCompatible implements CompatibilityState {
    public boolean isCompatibleWith(List<Component> items, Component component) {
        for (Component newItem : items) {
            if (!component.isCompatibleWith(newItem)) {
                return true; // If any component is not compatible, return true
            }
        }
        return false; // All components are compatible
    }
}



// Compatibility class using the State pattern
public class CompatibilityChecker {
    private CompatibilityState currentState;

    public CompatibilityChecker() {
        currentState = new Compatible(); // Set initial state to compatible
    }

    public void setState(CompatibilityState state) {
        this.currentState = state;
    }

    public boolean checkCompatibility(List<Component> items, Component component) {
        return currentState.isCompatibleWith(items, component);
    }
}
