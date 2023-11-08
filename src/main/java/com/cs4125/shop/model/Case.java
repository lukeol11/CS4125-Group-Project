package com.cs4125.shop.model;

import java.util.List;

public class Case extends Component {
    private String formFactor;
    private int driveBays;
    private int dime;

    public Case(String name, double price, Integer wattage, String formFactor, int driveBays, int dime) {
        super(name, price, wattage);
        this.formFactor = formFactor;
        this.driveBays = driveBays;
        this.dime = dime;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public int getDriveBays() {
        return driveBays;
    }

    public int getDime() {
        return dime;
    }

    //Check if the case can hold the graphics card
    public boolean isCompatibleWith(List<Component> component) {
        if (component instanceof GraphicsCard) {
            // Check if the GPU length is compatible with the case's dimensions
            int gpuLength = ((GraphicsCard) component).getGPULength();
            return gpuLength <= this.dime;
        }
        System.out.println("Run Case");
        return false;
    }
}
