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

    //Check if storage is compatible with any Motherboards in the cart
    public boolean isCompatibleWith(List<Component> component) {
        if (component instanceof Motherboard) {
            String storageType = ((Motherboard) component).getStorageType();
            return type.equals(storageType);
        }
        System.out.println("Run Storage");
        return true;
    }
}
