package com.cs4125.shop.model;

import java.util.List;

public class Storage extends Component {
    private int capacity;
    private String type; // e.g., HDD, SSD, NVMe

    public Storage(String name, double price, Integer wattage, int capacity, String type) {
        super(name, price, wattage);
        this.capacity = capacity;
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public boolean isCompatibleWith(List<Component> component) {
        boolean match = false;

        for (Component components: component) {
        if (components instanceof Motherboard) {
            //Compatibility based on Storage Types
            String storageType = ((Motherboard) components).getStorageType();
            if (type == storageType) {
                match = true;
            } else {
                return match;
            }
        }
    }
        //For all other components return true
        System.out.println("Run Storage");
        return match;
    }
}
