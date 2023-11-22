package com.cs4125.shop.model;

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
}
