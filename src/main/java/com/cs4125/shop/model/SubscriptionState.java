package com.cs4125.shop.model;

public enum SubscriptionState {
    STANDARD {
        public int calculateLoyaltyPoints(int points) {
            return points; // Standard loyalty points calculation
        }
    },
    SILVER {
        public int calculateLoyaltyPoints(int points) {
            return (int) (points * 1.5); // 1.5x loyalty points for silver
        }
    },
    GOLD {
        public int calculateLoyaltyPoints(int points) {
            return points * 3; // 3x loyalty points for gold
        }
    },
    PLATINUM {
        public int calculateLoyaltyPoints(int points) {
            return points * 5; // 5x loyalty points for platinum
        }
    };

    public abstract int calculateLoyaltyPoints(int points);
}
