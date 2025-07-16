package com.example.recipemanager3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.recipemanager3.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeListController {

    private final RecipeRepository recipeRepository;

    @GetMapping("/recipes")
    public String showRecipeList(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes";
    }
}
