package com.cs4125.shop.model;

import java.util.List;

//import org.hibernate.mapping.List;
// import com.cs4125.shop.shoppingcart.ShoppingCart;

public class PowerSupply extends Component {
    private int wattageOutput;
    private String efficiencyRating;

    public PowerSupply(String name, double price, Integer wattage, int wattageOutput, String efficiencyRating) {
        super(name, price, wattage);
        this.wattageOutput = wattageOutput;
        this.efficiencyRating = efficiencyRating;
    }

    public int getWattageOutput() {
        return wattageOutput;
    }

    public String getEfficiencyRating() {
        return efficiencyRating;
    }

    // @Override
    public boolean isCompatibleWith(List<Component> comp) {
        int totalWattage = 0;
        for (Component component : comp) {
            // Ensure that only components with a wattage value are considered
            if (component.getWattage() != null) {
                totalWattage += component.getWattage();
            }
        }
        System.out.println("Run PSU");
        return totalWattage <= wattageOutput;
    }
}
