package com.cs4125.shop.model;

public enum SubscriptionState {
    STANDARD {
        public int calculateLoyaltyPoints(int loyaltyPoints) {
            return loyaltyPoints; // Standard loyalty points calculation
        }
    },
    SILVER {
        public int calculateLoyaltyPoints(int loyaltyPoints) {
            return (int) (loyaltyPoints * 1.5); // 1.5x loyalty points for silver subscription
        }
    },
    GOLD {
        public int calculateLoyaltyPoints(int loyaltyPoints) {
            return loyaltyPoints * 3; // 3x loyalty points for gold subscription
        }
    },
    PLATINUM {
        public int calculateLoyaltyPoints(int loyaltyPoints) {
            return loyaltyPoints * 5; // 5x loyalty points for platinum subscription
        }
    };

    public abstract int calculateLoyaltyPoints(int loyaltyPoints);
}
