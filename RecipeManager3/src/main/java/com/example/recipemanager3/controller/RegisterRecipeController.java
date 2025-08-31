
package com.example.recipemanager3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.recipemanager3.entity.Category;
import com.example.recipemanager3.entity.Recipe;
import com.example.recipemanager3.entity.User;
import com.example.recipemanager3.repository.CategoryRepository;
import com.example.recipemanager3.repository.RecipeRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegisterRecipeController {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;

    @GetMapping("/registerRecipe")
    public String showRegisterRecipeForm(HttpSession session, Model model) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categories);
        return "registerRecipe";
    }

    @PostMapping("/registerRecipe")
    public String registerRecipe(
            @ModelAttribute("recipe") @Valid Recipe recipe,
            BindingResult bindingResult,
            HttpSession session,
            Model model) {

        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "registerRecipe"; // ← 入力エラー時に戻すテンプレート
        }

        User loginUser = (User) session.getAttribute("loginUser");
        recipe.setUserId(loginUser.getId());
        recipeRepository.save(recipe);
        return "redirect:/registerComplete";
    }
    
    @GetMapping("/registerComplete")
    public String showRegisterComplete(HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }
        return "registerComplete";
    }

}
