package com.cs4125.shop.controller;

import com.cs4125.shop.model.*;
import com.cs4125.shop.shoppingcart.ShoppingCart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        componentList.add(new CPU("Intel Core i7-9700K", 409.99, 95, 8, "LGA 1151"));
        componentList.add(new CPU("AMD Ryzen 7 3700X", 329.99, 65, 8, "AM4"));
        // ... (other component additions)
    }

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

    @PostMapping("/cart/add")
    public void addComponentToCart(@RequestParam("name") String componentName) {
        for (Component component : componentList) {
            if (component.getName().equals(componentName)) {
                cart.addComponent(component);
                break;
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

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam("username") String username,
            @RequestParam("useLoyaltyPoints") int useLoyaltyPoints) {
        User user = findUserByUsername(username);

        if (user != null) {
            double discount = useLoyaltyPoints;
            if (discount > user.getLoyaltyPoints()) {
                discount = user.getLoyaltyPoints();
            }

            user.deductLoyaltyPoints(discount);

            cart.clearCart();
            return ResponseEntity.ok("Checkout successful. Loyalty points used: " + discount + " euros.");
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
