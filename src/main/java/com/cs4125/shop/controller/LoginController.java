package com.cs4125.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs4125.shop.model.User;
import com.cs4125.shop.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    @Qualifier("loginServiceProxy")
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("errorMessage", ""); // Initialize error message
        return "login"; // Return the login form template.
    }

    @PostMapping("/loginactivity")
    public String loginUser(
        @RequestParam("email") String email,
        @RequestParam("password") String password,
        Model model
    ) {
        // Perform login validation and logic.
        User user = loginService.login(email, password);

        if (user != null) {
            // Successful login.
            System.out.println("User in session: " + user.getEmail());
            model.addAttribute("user", user);
            return "homepage"; // Redirect to the dashboard or any other page.
        } else {
            System.out.println("User not found");
            model.addAttribute("errorMessage", "Invalid email or password. Please try again.");
            return "login"; // Return to the login form with an error message.
        }
    }
}
