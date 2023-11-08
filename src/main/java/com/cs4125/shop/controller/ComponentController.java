package com.cs4125.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs4125.shop.model.CPU;
import com.cs4125.shop.model.Case;
import com.cs4125.shop.model.Component;
import com.cs4125.shop.model.GraphicsCard;
import com.cs4125.shop.model.Motherboard;
import com.cs4125.shop.model.PowerSupply;
import com.cs4125.shop.model.RAM;
import com.cs4125.shop.model.Storage;
import com.cs4125.shop.model.User;
import com.cs4125.shop.service.LoyaltyService;
import com.cs4125.shop.shoppingcart.ShoppingCart;

@RestController
@RequestMapping("/api")
public class ComponentController {
    private List<Component> componentList = new ArrayList<>();
    private ShoppingCart cart = new ShoppingCart();
    private List<User> userList = new ArrayList<>();

    public ComponentController() {
        componentList.add(new CPU("Intel Core i7-9700K", 409.99, 95, 8, "LGA 1151"));
        componentList.add(new CPU("AMD Ryzen 7 3700X", 329.99, 65, 8, "AM4"));
        componentList.add(new CPU("Intel Core i9-9900K", 529.99, 95, 8, "LGA 1151"));
        // motherboard
        componentList.add(new Motherboard("ASUS ROG Strix Z390-E", 279.99, 60, "Z390", "ATX"));
        componentList.add(new Motherboard("ASUS ROG Strix X570-E", 329.99, 60, "X570", "ATX"));
        componentList.add(new Motherboard("ASUS ROG Strix Z390-F", 229.99, 60, "Z390", "ATX"));
        // ram
        componentList.add(new RAM("Corsair Vengeance LPX 16GB", 79.99, 10, 16, 3200));
        componentList.add(new RAM("Corsair Vengeance LPX 32GB", 149.99, 10, 32, 3200));
        componentList.add(new RAM("Corsair Vengeance LPX 64GB", 299.99, 10, 64, 3200));
        // gpu
        componentList.add(new GraphicsCard("NVIDIA GeForce RTX 2080 Ti", 1199.99, 250, 11, 1350));
        componentList.add(new GraphicsCard("NVIDIA GeForce RTX 2070 Super", 499.99, 250, 8, 1605));
        componentList.add(new GraphicsCard("NVIDIA GeForce RTX 2060 Super", 399.99, 250, 8, 1470));
        // psu
        componentList.add(new PowerSupply("Corsair RMx Series RM750x", 129.99, 0, 750, "80+ Silver"));
        componentList.add(new PowerSupply("Corsair RMx Series RM850x", 149.99, 0, 850, "80+ Titanium"));
        componentList.add(new PowerSupply("Corsair RMx Series RM1000x", 179.99, 0, 1000, "80+ Bronze"));
        // case
        componentList.add(new Case("Corsair Carbide Series 275R", 79.99, 0, "ATX Full Tower", 2));
        componentList.add(new Case("Corsair Carbide Series 678C", 199.99, 0, "ATX Mid Tower", 6));
        componentList.add(new Case("Corsair Carbide Series 678C", 199.99, 0, "Mini ITX Desktop", 0));
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

    // Endpoint to add a component to the cart
    @PostMapping("/cart/add")
    public void addComponentToCart(@RequestParam("name") String componentName) {
        // search for component in componentList
        for (Component component : componentList) {
            if (component.getName().equals(componentName)) {
                cart.addComponent(component);
                break;
            }
        }
    }

    // Endpoint to get the items in the cart
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
        // Create a new User instance with the provided username and loyaltyPoints
        User newUser = new User(username, loyaltyPoints);

        // Add the new user to the userList
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

    // Calculate the total price of items in the cart
    private double calculateTotalCartPrice() {
        double totalPrice = 0.0;
        for (Component item : cart.getComponents()) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    @GetMapping("/cart/totalPrice")
    public ResponseEntity<Double> getCartTotalPrice() {
        double totalPrice = calculateTotalCartPrice();
        return ResponseEntity.ok(totalPrice);
    }

    @Autowired
    private LoyaltyService loyaltyService;

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam("username") String username) {
        User user = findUserByUsername(username);

        if (user != null) {
            double totalAmount = calculateTotalCartPrice();
            cart.clearCart(); // Clear the shopping cart after checkout
            loyaltyService.awardLoyaltyPoints(user, totalAmount);
            return ResponseEntity.ok("Checkout successful. Loyalty points awarded.");
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
