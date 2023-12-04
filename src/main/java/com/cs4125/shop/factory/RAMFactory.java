package com.cs4125.shop.factory;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.model.RAM;

public interface RAMFactory {
    public default Component createComponent(String name, double price, Integer wattage, int capacity, int speed) {
        return new RAM(name, price, wattage, capacity, speed);
    }
}