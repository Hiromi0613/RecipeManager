package com.example.recipemanager3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.recipemanager3.entity.Category;
import com.example.recipemanager3.entity.Recipe;
import com.example.recipemanager3.repository.CategoryRepository;
import com.example.recipemanager3.repository.RecipeRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;

    // 編集画面表示
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, HttpSession session, Model model) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        Recipe recipe = recipeRepository.findById(id);
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("recipe", recipe);           // ★ 編集対象のレシピ
        model.addAttribute("categories", categories);   // ★ カテゴリ一覧（プルダウン用）

        return "edit";
    }

    // 更新処理
    @PostMapping("/edit/{id}")
    public String updateRecipe(@PathVariable int id,
                               @ModelAttribute Recipe recipe,
                               HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        recipe.setId(id);
        recipe.setUserId(((com.example.recipemanager3.entity.User) session.getAttribute("loginUser")).getId());

        recipeRepository.update(recipe); // updateメソッドは別途作成

        return "redirect:/myrecipes";
    }
}
