package com.cs4125.shop.model;

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
}
