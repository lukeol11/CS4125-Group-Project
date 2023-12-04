package com.cs4125.shop.model;

import java.util.List;


public class Subscription extends Component {
    private SubscriptionState state;

    public Subscription(String name, double price, Integer wattage, SubscriptionState state) {
        super(name, price, wattage);
        this.state = state;
    }

    public SubscriptionState getState() {
        return state;
    }

    public int calculateLoyaltyPoints(int loyaltyPoints) {
        return state.calculateLoyaltyPoints(loyaltyPoints);
    }

    //Subscriptions aren't compatible, only one in the cart at a time
    public boolean isCompatibleWith(List<Component> items) {
        for (Component component: items) {
            if (component instanceof Subscription) {
                return false;
            }
        }
        return true;
    }
}
