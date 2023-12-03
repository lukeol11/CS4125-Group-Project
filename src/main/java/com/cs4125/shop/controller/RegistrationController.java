package com.cs4125.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs4125.shop.service.RegistrationResult;
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
        // Perform registration and validation logic.
        RegistrationResult registrationResult = registrationService.registerUser(email, password, confirmPassword);

        if (registrationResult == RegistrationResult.SUCCESS) {
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful!");
            return "redirect:/login"; // Redirect to the login page.
        } else {
            model.addAttribute("errorMessage", getErrorMessage(registrationResult));
            return "register"; // Return to the registration form with an error message.
        }
    }

    private String getErrorMessage(RegistrationResult registrationResult) {
        switch (registrationResult) {
            case INVALID_EMAIL:
                return "Invalid email address.";
            case INVALID_PASSWORD:
            return "Please ensure that your password:<br/><br/>" +
                "- Is between 8 and 16 characters long,<br/>" +
                "- Contains at least 1 uppercase letter,<br/>" +
                "- Contains at least 1 lowercase letter,<br/>" +
                "- Includes at least 1 special character,<br/>" +
                "- Includes at least 1 number.<br/><br/>" +
                "Thank you for choosing a secure password!";
            case EMAIL_ALREADY_REGISTERED:
                return "Email is already registered.";
            case PASSWORDS_DO_NOT_MATCH:
                return "Passwords do not match.";
            default:
                return "Registration failed. Please check your input.";
        }
    }
}
