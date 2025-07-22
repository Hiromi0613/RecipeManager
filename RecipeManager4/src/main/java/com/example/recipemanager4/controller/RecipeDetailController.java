package com.example.recipemanager4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.recipemanager4.entity.Recipe;
import com.example.recipemanager4.entity.Category;
import com.example.recipemanager4.repository.RecipeRepository;
import com.example.recipemanager4.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeDetailController {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository; // ✅ 追加

    @GetMapping("/recipe/{id}")
    public String showDetail(@PathVariable int id, Model model) {
        Recipe recipe = recipeRepository.findById(id);
        if (recipe == null) {
            return "redirect:/";
        }

        Category category = categoryRepository.findById(recipe.getCategoryId());
        String categoryName = (category != null) ? category.getName() : "未設定";
        model.addAttribute("recipe", recipe);
        model.addAttribute("categoryName", categoryName);

        return "recipeDetail";
    }
}
