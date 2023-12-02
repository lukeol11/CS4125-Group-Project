package com.cs4125.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs4125.shop.model.User;
import com.cs4125.shop.service.RegistrationService;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Return the register form template.
    }

    @PostMapping("/register")
    public String registerUser(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        @RequestParam("confirmPassword") String confirmPassword,
        Model model,
        RedirectAttributes redirectAttributes
    ) {
        // Create a User object from the input data.
        User user = new User(email, password, 0);

        // Perform registration and validation logic.
        boolean registrationSuccess = registrationService.registerUser(user, confirmPassword);
        System.out.println("registrationSuccess--->"+registrationSuccess);

        if (registrationSuccess) {
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful!");
            return "redirect:/login"; // Redirect to the login page.
        } else {
            model.addAttribute("errorMessage", "Registration failed. Please check your input.");
            return "register"; // Return to the registration form with an error message.
        }
    }
}