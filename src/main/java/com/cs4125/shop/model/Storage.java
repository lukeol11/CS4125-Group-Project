package com.cs4125.shop.model;

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

    public boolean isCompatibleWith(Component component) {
        if (component instanceof Motherboard) {
            //Compatibility based on Storage Types
            String storageType = ((Motherboard) component).getStorageType();
            return type.equals(storageType);
        }
        //For all other components return true
        System.out.println("Run Storage");
        return true;
    }
}
