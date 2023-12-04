package com.cs4125.shop.model;

import java.util.List;

import com.cs4125.shop.model.factory.CaseFactory;

public class Case extends Component implements CaseFactory {
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

    //Check if the case can hold the graphics card that is in the cart
    public boolean isCompatibleWith(List<Component> component) {
        if (component instanceof GraphicsCard) {
            int gpuLength = ((GraphicsCard) component).getGPULength();
            if (gpuLength >= this.dime) {
                return false;
            }
        }
        System.out.println("Run Case");
        return true;
    }
}
