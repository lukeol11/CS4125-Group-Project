package com.cs4125.shop.model.factory;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.model.GraphicsCard;

public interface GraphicsCardFactory {
    public default Component createComponent(String name, double price, Integer wattage, int memory, int baseClock) {
        return new GraphicsCard(name, price, wattage, memory, baseClock);
    }
}