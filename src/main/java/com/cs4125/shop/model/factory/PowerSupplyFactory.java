package com.cs4125.shop.model.factory;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.model.PowerSupply;

public interface PowerSupplyFactory {
    public default Component createComponent(String name, double price, Integer wattage, int wattageOutput,
            String efficiencyRating) {
        return new PowerSupply(name, price, wattage, wattageOutput, efficiencyRating);
    }
}