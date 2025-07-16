package com.example.recipemanager3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.recipemanager3.entity.User;
import com.example.recipemanager3.repository.UserRepository;

@Controller
public class RegisterAccountController {

    private final UserRepository userRepository;

    public RegisterAccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registerAccount")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "registerAccount";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("error", "このメールアドレスはすでに使われています");
            return "registerAccount";
        }

        userRepository.save(user);
        return "redirect:/login";
    }
}
