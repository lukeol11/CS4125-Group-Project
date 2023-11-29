package com.cs4125.shop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComponentControllerTest {
/*
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

        // Assert
        ResponseEntity<List<Map<String, Object>>> responseEntity = restTemplate.exchange("/api/cart",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Map<String, Object>>>() {
                });

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode(), "HTTP Status should be OK");
        List<Map<String, Object>> cartComponents = responseEntity.getBody();
        assertNotNull(cartComponents, "Cart components should not be null");
        assertEquals(1, cartComponents.size(), "Expected 1 component in the cart");
        assertEquals("Intel Core i7-9700K", cartComponents.get(0).get("name"), "Component name in the cart mismatch");
    }
    */
}
