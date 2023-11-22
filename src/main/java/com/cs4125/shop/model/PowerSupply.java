package com.cs4125.shop.model;

import java.util.List;

import com.cs4125.shop.shoppingcart.ShoppingCart;
import com.cs4125.shop.model.factory.PowerSupplyFactory;

public class PowerSupply extends Component implements PowerSupplyFactory {
    private int wattageOutput;
    private String efficiencyRating;

    public PowerSupply(String name, double price, Integer wattage, int wattageOutput, String efficiencyRating) {
        super(name, price, wattage);
        this.wattageOutput = wattageOutput;
        this.efficiencyRating = efficiencyRating;
    }

    public int getWattageOutput() {
        return wattageOutput;
    }

    public String getEfficiencyRating() {
        return efficiencyRating;
    }

    public boolean isCompatibleWith(ShoppingCart shoppingCart) {
        int totalWattage = 0;
        List<Component> cart = shoppingCart.getComponents();
        for (Component component : cart) {
            // Ensure that only components with a wattage value are considered
            if (component.getWattage() != null) {
                totalWattage += component.getWattage();
            }
        }
        System.out.println("Run PSU");
        return totalWattage <= wattageOutput;
    }

    public boolean isCompatibleWith(Component component){
        return false;
    }
}
