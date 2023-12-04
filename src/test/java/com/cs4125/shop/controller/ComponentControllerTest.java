package com.cs4125.shop.controller;

import com.cs4125.shop.services.CartTotal;
import com.cs4125.shop.shoppingcart.ShoppingCart;
import com.cs4125.shop.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComponentControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetComponentsEndpoint() {
        // Act
        ParameterizedTypeReference<List<Map<String, Object>>> responseType = new ParameterizedTypeReference<List<Map<String, Object>>>() {
        };
        ResponseEntity<List<Map<String, Object>>> responseEntity = restTemplate.exchange("/api/components",
                HttpMethod.GET, null, responseType);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "HTTP Status should be OK");
        List<Map<String, Object>> componentInfoList = responseEntity.getBody();
        assertNotNull(componentInfoList, "Response body should not be null");

        // components
        assertEquals(21, componentInfoList.size(), "Expected 21 components in the response");
        assertEquals("Intel Core i7-9700K", componentInfoList.get(0).get("name"), "First component name mismatch");
        assertEquals("CPU", componentInfoList.get(0).get("type"), "First component type mismatch");

        assertEquals("AMD Ryzen 7 3700X", componentInfoList.get(1).get("name"), "Second component name mismatch");
        assertEquals("CPU", componentInfoList.get(1).get("type"), "Second component type mismatch");
    }

    @Test
    public void testAddComponentToCart() {
        // Arrange
        String componentName = "Intel Core i7-9700K";

        // Act
        restTemplate.postForEntity("/api/cart/add?name={name}", null, Void.class, componentName);

        // Clear the cart to start fresh
        restTemplate.delete("/api/cart");

        // Assert
        ResponseEntity<List<Map<String, Object>>> responseEntity = restTemplate.exchange("/api/cart", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Map<String, Object>>>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "HTTP Status should be OK");
        List<Map<String, Object>> cartComponents = responseEntity.getBody();
        assertNotNull(cartComponents, "Cart components should not be null");

        assertEquals(1, cartComponents.size(), "Expected 1 component in the cart");
        assertEquals("Intel Core i7-9700K", cartComponents.get(0).get("name"),
                "Component name in the cart mismatch");
    }

    @Test
    public void testCheckoutEndpoint() {
        // Arrange
        String email = "cathal@gmail.com";
        double useLoyaltyPoints = 0.0;

        // Act
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "/api/checkout?email={email}&useLoyaltyPoints={useLoyaltyPoints}",
                null,
                String.class,
                email,
                useLoyaltyPoints);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "HTTP Status should be OK");
        String responseBody = responseEntity.getBody();
        assertNotNull(responseBody, "Response body should not be null");

        assertTrue(responseBody.contains("Checkout successful"), "Expected success message in the response");
    }

    @Test
    public void testLoyaltyPointsEarnedAtCheckout() {
        // Arrange
        String email = "cathal@gmail.com";

        restTemplate.postForEntity("/api/cart/add?name=Intel Core i7-9700K", null, Void.class);
        ShoppingCart cart = new ShoppingCart();

        // Act
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                "/api/checkout?email={email}&useLoyaltyPoints={useLoyaltyPoints}",
                null,
                String.class,
                email,
                0.0);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "HTTP Status should be OK");
        String responseBody = responseEntity.getBody();
        assertNotNull(responseBody, "Response body should not be null");

        CartTotal cartTotal = new CartTotal(cart);

        assertTrue(responseBody.contains("Loyalty points earned"), "Expected loyalty points message in the response");

        // Verify user's loyalty points after checkout
        ResponseEntity<User> userResponse = restTemplate.getForEntity("/api/user/find/{email}", User.class, email);
        assertEquals(HttpStatus.OK, userResponse.getStatusCode(), "HTTP Status should be OK");
        User user = userResponse.getBody();
        assertNotNull(user, "User should not be null");

        // Calculate the expected loyalty points based on 10% of the total price
        double expectedLoyaltyPoints = user.getLoyaltyPoints() +
                (cartTotal.calculateTotalCartPrice() * 0.1);

        assertEquals(expectedLoyaltyPoints, user.getLoyaltyPoints(),
                "Loyalty points should be updated after checkout with 10% of the total price");
    }
}
