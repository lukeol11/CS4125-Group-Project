package com.cs4125.shop.model;

import java.util.List;

public abstract class Component {
    private String name;
    private double price;
    private Integer wattage;


    // Constructor for Component class
    public Component(String name, double price, Integer wattage) {
        this.name = name;
        this.price = price;
        this.wattage = wattage;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public Integer getWattage() {
        return this.wattage;
    }

    //Abstract compatibility check in the super class for all components to use
    public abstract boolean isCompatibleWith(List<Component> component);
}
