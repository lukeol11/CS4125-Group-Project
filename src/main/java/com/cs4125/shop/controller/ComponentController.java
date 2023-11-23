package com.cs4125.shop.controller;

import com.cs4125.shop.model.*;
import com.cs4125.shop.shoppingcart.ShoppingCart;

// import org.hibernate.engine.internal.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ComponentController {
    private List<Component> componentList = new ArrayList<>();
    private ShoppingCart cart = new ShoppingCart();
    private List<User> userList = new ArrayList<>();

    public ComponentController() {
        componentList.add(new CPU("Intel Core i7-9700K", 409.99, 95, 8, "LGA 1151", 3200));
        componentList.add(new CPU("AMD Ryzen 7 3700X", 329.99, 65, 8, "AM4", 3200));
        componentList.add(new CPU("Intel Core i9-9900K", 529.99, 95, 8, "LGA 1151", 3600));
        // motherboard
        componentList.add(new Motherboard("ASUS ROG Strix Z390-E", 279.99, 60, "Z390", "ATX", "LGA 1151", "M.2"));
        componentList.add(new Motherboard("ASUS ROG Strix X570-E", 329.99, 60, "X570", "ATX", "AM4", "M.2"));
        componentList.add(new Motherboard("ASUS ROG Strix Z390-F", 229.99, 60, "Z390", "ATX", "LGA 1151", "M.2"));
        // ram
        componentList.add(new RAM("Corsair Vengeance LPX 16GB", 79.99, 10, 16, 3200));
        componentList.add(new RAM("Corsair Vengeance LPX 32GB", 149.99, 10, 32, 3200));
        componentList.add(new RAM("Corsair Vengeance LPX 64GB", 299.99, 10, 64, 3600));
        // gpu
        componentList.add(new GraphicsCard("NVIDIA GeForce RTX 2080 Ti", 1199.99, 250, 11, 1350, 135));
        componentList.add(new GraphicsCard("NVIDIA GeForce RTX 2070 Super", 499.99, 250, 8, 1605, 135));
        componentList.add(new GraphicsCard("NVIDIA GeForce RTX 2060 Super", 399.99, 250, 8, 1470, 100));
        // psu
        componentList.add(new PowerSupply("Corsair RMx Series RM750x", 129.99, 0, 750, "80+ Silver"));
        componentList.add(new PowerSupply("Corsair RMx Series RM850x", 149.99, 0, 850, "80+ Titanium"));
        componentList.add(new PowerSupply("Corsair RMx Series RM1000x", 179.99, 0, 1000, "80+ Bronze"));
        // case
        componentList.add(new Case("Corsair Carbide Series 275R", 79.99, 0, "ATX Full Tower", 2, 135));
        componentList.add(new Case("Corsair Carbide Series 678C", 199.99, 0, "ATX Mid Tower", 6, 135));
        componentList.add(new Case("Corsair Carbide Series 678C", 199.99, 0, "Mini ITX Desktop", 0, 100));
        // storage
        componentList.add(new Storage("Samsung 970 Evo 1TB", 169.99, 10, 1000, "M.2"));
        componentList.add(new Storage("Samsung 970 Evo 2TB", 349.99, 10, 2000, "M.2"));
        componentList.add(new Storage("Samsung 970 Evo 4TB", 749.99, 10, 4000, "HDD"));
    }

    // Endpoint to retrieve a list of components
    @GetMapping("/components")
    public List<Map<String, Object>> getComponents() {
        List<Map<String, Object>> componentInfoList = new ArrayList<>();

        for (Component component : componentList) {
            Map<String, Object> componentInfo = new HashMap<>();
            componentInfo.put("name", component.getName());
            componentInfo.put("type", component.getClass().getSimpleName());

            componentInfoList.add(componentInfo);
        }

        return componentInfoList;
    }

    // Add component to cart if it checks out
    @PostMapping("/cart/add")
    public void addComponentToCart(@RequestParam("name") String componentName) {
    for (Component component : componentList) {
        if (component.getName().equals(componentName)) {
            // Check compatibility
            if (cart.checkCompatibility(Collections.singletonList(component), component)) {
                System.out.println("before");
                cart.addComponent(component);
                System.out.println("Run in the Controller yuppa");
            } else {
                System.out.println("Component is not compatible with the relevant items in the cart");
            }
        }
    }
}


    @GetMapping("/cart")
    public List<Component> getCartComponents() {
        return cart.getComponents();
    }

    @GetMapping("/getLoyaltyPoints")
    public ResponseEntity<?> getLoyaltyPoints(@RequestParam("username") String username) {
        User user = findUserByUsername(username);

        if (user != null) {
            return ResponseEntity.ok(user.getLoyaltyPoints());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
        }
    }

    @PostMapping("/users/create")
    public ResponseEntity<String> createUser(
            @RequestParam("username") String username,
            @RequestParam("loyaltyPoints") int loyaltyPoints) {
        User newUser = new User(username, loyaltyPoints);
        userList.add(newUser);

        return ResponseEntity.ok("User created successfully.");
    }

    private User findUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private double calculateTotalCartPrice() {
        double totalPrice = 0.0;
        for (Component item : cart.getComponents()) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam("username") String username,
            @RequestParam("useLoyaltyPoints") int useLoyaltyPoints) {
        User user = findUserByUsername(username);

        if (user != null) {
            double totalAmount = calculateTotalCartPrice();

            double discount = useLoyaltyPoints;
            if (discount > user.getLoyaltyPoints()) {
                discount = user.getLoyaltyPoints();
            }

            int pointsAwarded = (int) (totalAmount / 10);
            user.addLoyaltyPoints(pointsAwarded);

            totalAmount -= discount;

            user.deductLoyaltyPoints(discount);

            cart.clearCart();
            return ResponseEntity.ok("Checkout successful. Loyalty points used: " + discount
                    + " euros. Loyalty points earned: " + pointsAwarded);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
        }
    }

    @GetMapping("/users/find")
    public ResponseEntity<User> findUser(@RequestParam("username") String username) {
        User user = findUserByUsername(username);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
