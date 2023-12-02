package com.cs4125.shop.model;

public class Subscription extends Component {
    private SubscriptionState state;

    public Subscription(String name, double price, Integer wattage) {
        super(name, price, wattage);
    }

    public SubscriptionState getState() {
        return state;
    }

    public int calculateLoyaltyPoints(int points) {
        return state.calculateLoyaltyPoints(points);
    }
}
