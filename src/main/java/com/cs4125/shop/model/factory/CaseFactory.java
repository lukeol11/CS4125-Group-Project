package com.cs4125.shop.model.factory;

import com.cs4125.shop.model.Case;
import com.cs4125.shop.model.Component;

public interface CaseFactory {
    public default Component createComponent(String name, double price, Integer wattage, String formFactor,
            int driveBays) {
        return new Case(name, price, wattage, formFactor, driveBays);
    }
}
