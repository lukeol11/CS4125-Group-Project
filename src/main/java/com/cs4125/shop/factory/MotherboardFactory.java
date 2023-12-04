package com.cs4125.shop.factory;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.model.Motherboard;

public interface MotherboardFactory {
    public default Component createComponent(String name, double price, Integer wattage, String chipset,
            String formFactor, String socket,
            String storageType) {
        return new Motherboard(name, price, wattage, chipset, formFactor, socket, storageType);
    }
}