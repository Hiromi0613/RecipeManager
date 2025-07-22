package com.example.recipemanager3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.recipemanager3.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("loginUser", loginUser);
        return "home";
    }
}