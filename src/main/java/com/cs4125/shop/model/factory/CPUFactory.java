package com.cs4125.shop.model.factory;

import com.cs4125.shop.model.CPU;
import com.cs4125.shop.model.Component;

public interface CPUFactory {
    public default Component createComponent(String name, double price, Integer wattage, int cores, String socketCPU,
            int speedCPU) {
        return new CPU(name, price, wattage, cores, socketCPU, speedCPU);
    }
}