package com.cs4125.shop.model;

import java.util.List;

public class RAM extends Component {
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
        if (component instanceof CPU) {
            int speedCPU = ((CPU) component).getSpeedCPU();
            return this.speed == speedCPU;
        }
        System.out.println("Run RAM");
        return true;
    }
}
