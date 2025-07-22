package com.example.recipemanager3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.recipemanager3.entity.Category;
import com.example.recipemanager3.entity.Recipe;
import com.example.recipemanager3.repository.CategoryRepository;
import com.example.recipemanager3.repository.RecipeRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    public CategoryController(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
    }

    // カテゴリ一覧ページの表示
    @GetMapping("/category")
    public String showCategoryPage(HttpSession session, Model model) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "category";
    }

    // カテゴリに属するレシピ一覧を表示
    @GetMapping("/category/{id}")
    public String showRecipesByCategory(@PathVariable("id") int categoryId, HttpSession session, Model model) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }
        List<Recipe> recipes = recipeRepository.findByCategoryId(categoryId);
        Category category = categoryRepository.findById(categoryId);
        model.addAttribute("recipes", recipes);
        model.addAttribute("categoryName", category.getName());
        return "categoryRecipes";
    }
}