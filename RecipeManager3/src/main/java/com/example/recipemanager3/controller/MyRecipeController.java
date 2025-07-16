package com.example.recipemanager3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.recipemanager3.entity.User;
import com.example.recipemanager3.repository.RecipeRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyRecipeController {

    private final RecipeRepository recipeRepository;

    @GetMapping("/myrecipes")
    public String showMyRecipes(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("recipes", recipeRepository.findByUserId(loginUser.getId()));
        return "myrecipes"; // → templates/myrecipes.html を表示
    }
}
