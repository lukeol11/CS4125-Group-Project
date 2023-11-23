package com.cs4125.shop.model;

import com.cs4125.shop.model.factory.MotherboardFactory;

public class Motherboard extends Component implements MotherboardFactory {
    private String chipset;
    private String formFactor;

    public Motherboard(String name, double price, Integer wattage, String chipset, String formFactor) {
        super(name, price, wattage);
        this.chipset = chipset;
        this.formFactor = formFactor;
    }

    public String getChipset() {
        return chipset;
    }

    public String getFormFactor() {
        return formFactor;
    }
}
