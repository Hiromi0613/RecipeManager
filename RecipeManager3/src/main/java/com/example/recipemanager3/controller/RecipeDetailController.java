package com.example.recipemanager3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.recipemanager3.entity.Recipe;
import com.example.recipemanager3.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeDetailController {

    private final RecipeRepository recipeRepository;

    @GetMapping("/recipe/{id}")
    public String showDetail(@PathVariable int id, Model model) {
        Recipe recipe = recipeRepository.findById(id);
        if (recipe == null) {
            return "redirect:/"; // 見つからない場合はトップへ
        }
        model.addAttribute("recipe", recipe);
        return "recipeDetail"; // → templates/recipeDetail.html
    }
}
