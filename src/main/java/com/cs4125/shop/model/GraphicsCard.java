package com.cs4125.shop.model;

import com.cs4125.shop.model.factory.GraphicsCardFactory;

public class GraphicsCard extends Component implements GraphicsCardFactory {
    private int memory;
    private int baseClock;

    public GraphicsCard(String name, double price, Integer wattage, int memory, int baseClock) {
        super(name, price, wattage);
        this.memory = memory;
        this.baseClock = baseClock;
    }

    public int getMemory() {
        return memory;
    }

    public int getBaseClock() {
        return baseClock;
    }
}
