package com.cs4125.shop.model;

import java.util.List;

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

    //Check if the total wattage of components in the cart exceeds the PSU limit
    public boolean isCompatibleWith(List<Component> comp) {
        int totalWattage = 0;
        for (Component component : comp) {
            if (component.getWattage() != null) {
                totalWattage += component.getWattage();
            }
        }
        System.out.println("Run PSU");
        return totalWattage <= wattageOutput;
    }
}
