package com.cs4125.shop.model.factory;

import com.cs4125.shop.model.Component;
import com.cs4125.shop.model.Subscription;
import com.cs4125.shop.model.SubscriptionState;

public interface SubscriptionFactory {
    public default Component createComponent(String name, double price, Integer wattage, SubscriptionState state) {
        return new Subscription(name, price, wattage, state);
    }
}
