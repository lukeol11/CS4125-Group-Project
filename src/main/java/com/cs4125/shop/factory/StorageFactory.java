package com.cs4125.shop.factory;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.model.Storage;

public interface StorageFactory {
    public default Component createComponent(String name, double price, Integer wattage, int capacity, String type) {
        return new Storage(name, price, wattage, capacity, type);
    }
}