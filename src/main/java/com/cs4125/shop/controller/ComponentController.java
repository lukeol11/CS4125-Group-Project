package com.cs4125.shop.controller;

import com.cs4125.shop.model.*;
import com.cs4125.shop.model.factory.UserFactory;
import com.cs4125.shop.model.factory.CPUFactory;
import com.cs4125.shop.model.factory.GraphicsCardFactory;
import com.cs4125.shop.model.factory.MotherboardFactory;
import com.cs4125.shop.model.factory.PowerSupplyFactory;
import com.cs4125.shop.model.factory.RAMFactory;
import com.cs4125.shop.model.factory.CaseFactory;
import com.cs4125.shop.model.factory.StorageFactory;
import com.cs4125.shop.shoppingcart.ShoppingCart;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cs4125.shop.services.CartTotal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ComponentController {
    private List<Component> componentList = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();

    private ShoppingCart cart = new ShoppingCart();
    private Compatibility compatibility = new Compatibility();
    private List<User> userList = new ArrayList<>();

    private UserFactory userFactory = new UserFactory() {
    };
    private CPUFactory cpuFactory = new CPUFactory() {
    };
    private GraphicsCardFactory graphicsCardFactory = new GraphicsCardFactory() {
    };
    private MotherboardFactory motherboardFactory = new MotherboardFactory() {
    };
    private PowerSupplyFactory powerSupplyFactory = new PowerSupplyFactory() {
    };
    private RAMFactory ramFactory = new RAMFactory() {
    };
    private StorageFactory storageFactory = new StorageFactory() {
    };
    private CaseFactory caseFactory = new CaseFactory() {
    };

    public ComponentController() {
        componentList.add(cpuFactory.createComponent("Intel Core i7-9700K", 409.99, 95, 8, "LGA 1151", 3200));
        componentList.add(cpuFactory.createComponent("AMD Ryzen 7 3700X", 329.99, 65, 8, "AM4", 3200));
        componentList.add(cpuFactory.createComponent("Intel Core i9-9900K", 529.99, 95, 8, "LGA 1151", 3600));
        componentList.add(motherboardFactory.createComponent("ASUS ROG Strix Z390-E", 279.99, 60, "Z390", "ATX",
                "LGA 1151", "M.2"));
        componentList.add(
                motherboardFactory.createComponent("ASUS ROG Strix X570-E", 329.99, 60, "X570", "ATX", "AM4", "M.2"));
        componentList.add(motherboardFactory.createComponent("ASUS ROG Strix Z390-F", 229.99, 60, "Z390", "ATX",
                "LGA 1151", "HDD"));
        componentList.add(ramFactory.createComponent("Corsair Vengeance LPX 16GB", 79.99, 10, 16, 3200));
        componentList.add(ramFactory.createComponent("Corsair Vengeance LPX 32GB", 149.99, 10, 32, 3200));
        componentList.add(ramFactory.createComponent("Corsair Vengeance LPX 64GB", 299.99, 10, 64, 3600));
        componentList
                .add(graphicsCardFactory.createComponent("NVIDIA GeForce RTX 2080 Ti", 1199.99, 250, 11, 1350, 135));
        componentList
                .add(graphicsCardFactory.createComponent("NVIDIA GeForce RTX 2070 Super", 499.99, 250, 8, 1605, 135));
        componentList
                .add(graphicsCardFactory.createComponent("NVIDIA GeForce RTX 2060 Super", 399.99, 250, 8, 1470, 100));
        componentList
                .add(powerSupplyFactory.createComponent("Corsair RMx Series RM750x", 129.99, 0, 750, "80+ Silver"));
        componentList
                .add(powerSupplyFactory.createComponent("Corsair RMx Series RM850x", 149.99, 0, 850, "80+ Titanium"));
        componentList
                .add(powerSupplyFactory.createComponent("Corsair RMx Series RM1000x", 179.99, 0, 1000, "80+ Bronze"));
        componentList
                .add(caseFactory.createComponent("Corsair Carbide Series 275R", 79.99, 0, "ATX Full Tower", 2, 135));
        componentList
                .add(caseFactory.createComponent("Corsair Carbide Series 678C", 199.99, 0, "ATX Mid Tower", 6, 135));
        componentList
                .add(caseFactory.createComponent("Corsair Carbide Series 678C", 199.99, 0, "Mini ITX Desktop", 0, 100));
        componentList.add(storageFactory.createComponent("Samsung 970 Evo 1TB", 169.99, 10, 1000, "M.2"));
        componentList.add(storageFactory.createComponent("Samsung 970 Evo 2TB", 349.99, 10, 2000, "M.2"));
        componentList.add(storageFactory.createComponent("Samsung 970 Evo 4TB", 749.99, 10, 4000, "HDD"));

        BulkOrderDiscount bulkOrderDiscount = new BulkOrderDiscount(true, 1000, new CartTotal(cart));

        discounts.add(bulkOrderDiscount);

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
                // check compatibility
                if (compatibility.isCompatibleWith(componentList, cart)) {
                    System.out.println("before");
                    cart.addComponent(component);
                    System.out.println("Run in the Controller yuppa");
                } else {
                    System.out.println("Run");
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
        User newUser = userFactory.createUser(username, loyaltyPoints);
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
            CartTotal cartTotal = new CartTotal(cart);
            double totalAmount = cartTotal.calculateTotalCartPrice();

            // Applying loyalty points discount
            double discount = useLoyaltyPoints;
            if (discount > user.getLoyaltyPoints()) {
                discount = user.getLoyaltyPoints();
            }

            int pointsAwarded = (int) (totalAmount / 10);
            user.addLoyaltyPoints(pointsAwarded);

            user.deductLoyaltyPoints(discount);

            // Applying dynamic discounts
            double discountedAmount = totalAmount;

            for (Discount discountObj : discounts) {
                if (discountObj.isApplicable()) {
                    discountedAmount = discountObj.applyDiscount(discountedAmount);
                    System.out.println("Applied Discount: " + discountObj.getDescription());
                }
            }

            cart.clearCart();

            return ResponseEntity.ok("Checkout successful. Loyalty points used: " + discount
                    + " euros. Loyalty points earned: " + pointsAwarded +
                    "\nTotal Amount after Discounts: " + discountedAmount);
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
