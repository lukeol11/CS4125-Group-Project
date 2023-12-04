package com.cs4125.shop.model;

import java.util.List;

import com.cs4125.shop.factory.CPUFactory;

public class CPU extends Component implements CPUFactory {
    private int cores;
    private String socketCPU;
    private int speedCPU;

    // Constructor for CPU class
    public CPU(String name, double price, Integer wattage, int cores, String socketCPU, int speedCPU) {
        super(name, price, wattage); // Call the constructor of the superclass (Component)
        this.cores = cores;
        this.socketCPU = socketCPU;
        this.speedCPU = speedCPU;
    }

    public int getCores() {
        return cores;
    }

    public String getSocketCPU() {
        return socketCPU;
    }

    public int getSpeedCPU() {
        return speedCPU;
    }

    public boolean isCompatibleWith(List<Component> component) {
        if (component instanceof Motherboard) {
            String socket = ((Motherboard) component).getSocket();
            return this.socketCPU.equals(socket);
        } else if (component instanceof RAM) {
            int speed = ((RAM) component).getSpeed();
            return this.speedCPU == speed;
        }
        System.out.println("Run CPU");
        return true;
    }
}
