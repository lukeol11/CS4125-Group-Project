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

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserController userController;

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

        Discount sameItemDiscount = new SameItemDiscountDecorator(new BaseDiscount(), cart);
        Discount thresholdDiscount = new ThresholdDiscountDecorator(new BaseDiscount(), 1000, 15, new CartTotal(cart));

        addDiscount(sameItemDiscount);
        addDiscount(thresholdDiscount);

    }

    public void addDiscount(Discount discount) {
        discounts.add(discount);
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

    @GetMapping("/add")
    public void addComponentToCart(@RequestParam("name") String componentName) {
        System.out.println(componentName);
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

    @PostMapping("/checkout")
public ResponseEntity<String> checkout(@RequestParam("email") String email,
        @RequestParam("useLoyaltyPoints") int useLoyaltyPoints) {
    ResponseEntity<User> userResponse = userController.getUserByEmail(email);

    if (userResponse.getStatusCode() == HttpStatus.OK) {
        User user = userResponse.getBody();

        CartTotal cartTotal = new CartTotal(cart); // Create an instance of CartTotal
        double totalAmount = cartTotal.calculateTotalCartPrice();

        double discount = useLoyaltyPoints;

        // Check if 'user' is not null before accessing 'getLoyaltyPoints()'
        if (user != null && discount > user.getLoyaltyPoints()) {
            discount = user.getLoyaltyPoints();
        }

        int pointsAwarded = (int) (totalAmount / 10);

        // Check if 'user' is not null before invoking 'addLoyaltyPoints'
        if (user != null) {
            user.addLoyaltyPoints(pointsAwarded);
        
            user.deductLoyaltyPoints(discount);
        }


        cart.clearCart();
        return ResponseEntity.ok("Checkout successful. Loyalty points used: " + discount
                + " euros. Loyalty points earned: " + pointsAwarded);
    } else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found.");
    }
}

    @GetMapping("/user/find/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        ResponseEntity<User> response = userController.getUserByEmail(email);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(response.getBody());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
