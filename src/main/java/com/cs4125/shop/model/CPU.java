package com.cs4125.shop.model;

import com.cs4125.shop.model.factory.CPUFactory;

public class CPU extends Component implements CPUFactory {
    private int cores;
    private String socket;

    // Constructor for CPU class
    public CPU(String name, double price, Integer wattage, int cores, String socket) {
        super(name, price, wattage); // Call the constructor of the superclass (Component)
        this.cores = cores;
        this.socket = socket;
    }

    public int getCores() {
        return cores;
    }

    public String getSocket() {
        return socket;
    }
}
