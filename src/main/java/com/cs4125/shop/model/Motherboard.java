package com.cs4125.shop.model;

import java.util.List;

import com.cs4125.shop.model.factory.MotherboardFactory;

public class Motherboard extends Component implements MotherboardFactory {
    private String chipset;
    private String formFactor;
    private String socket;
    private String storageType;

    public Motherboard(String name, double price, Integer wattage, String chipset, String formFactor, String socket,
            String storageType) {
        super(name, price, wattage);
        this.chipset = chipset;
        this.formFactor = formFactor;
        this.socket = socket;
        this.storageType = storageType;
    }

    public String getChipset() {
        return chipset;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public String getSocket() {
        return socket;
    }

    public String getStorageType() {
        return storageType;
    }

    //Check if Motherboard is compatilble with existing CPU or Storage in the cart
    public boolean isCompatibleWith(List<Component> component) {
        if (component instanceof CPU) {
            String socketCPU = ((CPU) component).getSocketCPU();
            return this.socket.equals(socketCPU);
        } else if (component instanceof Storage) {
            String type = ((Storage) component).getType();
            return this.storageType.equals(type);
        }
        System.out.println("Run Mother");
        return true;
    }
}
