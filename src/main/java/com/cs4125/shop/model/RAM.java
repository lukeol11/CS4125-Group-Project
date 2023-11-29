package com.cs4125.shop.model;

import java.util.List;

import com.cs4125.shop.model.factory.RAMFactory;

public class RAM extends Component implements RAMFactory {
    private int capacity;
    private int speed;

    public RAM(String name, double price, Integer wattage, int capacity, int speed) {
        super(name, price, wattage);
        this.capacity = capacity;
        this.speed = speed;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isCompatibleWith(List<Component> component) {
        boolean match = false;

    for(Component components: component) {
        if (components instanceof CPU) {
            int speedCPU = ((CPU) components).getSpeedCPU();
            if (this.speed == speedCPU) {
                System.out.println("Check");
                match = true;
            } else {
                return match;
            }
        }
    }
        System.out.println("Run RAM");
        return match;
    }
}
