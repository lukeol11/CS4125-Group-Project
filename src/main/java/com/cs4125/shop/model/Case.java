package com.cs4125.shop.model;

import com.cs4125.shop.model.factory.CaseFactory;

public class Case extends Component implements CaseFactory {
    private String formFactor;
    private int driveBays;

    public Case(String name, double price, Integer wattage, String formFactor, int driveBays) {
        super(name, price, wattage);
        this.formFactor = formFactor;
        this.driveBays = driveBays;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public int getDriveBays() {
        return driveBays;
    }
}
