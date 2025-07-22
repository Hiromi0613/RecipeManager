package com.example.recipemanager4.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.recipemanager4.entity.Category;
import com.example.recipemanager4.entity.Recipe;
import com.example.recipemanager4.entity.User;
import com.example.recipemanager4.repository.CategoryRepository;
import com.example.recipemanager4.repository.RecipeRepository;

import jakarta.servlet.http.HttpSession;
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
    public String registerRecipe(@ModelAttribute Recipe recipe,
                                 HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        recipe.setUserId(loginUser.getId());  // ← 追加！

        recipeRepository.save(recipe);

        return "redirect:/registerComplete"; // ← 登録完了画面へ
    }
    
    @GetMapping("/registerComplete")
    public String showRegisterComplete(HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }
        return "registerComplete";
    }

}
