package com.example.recipemanager3.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.recipemanager3.entity.Recipe;
import com.example.recipemanager3.repository.RecipeRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final RecipeRepository recipeRepository;

    @GetMapping("/search")
    public String search(
            @RequestParam(name = "keyword", required = false) String keyword,
            Model model,
            HttpSession session
    ) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }

        List<Recipe> recipes = null;
        if (keyword != null && !keyword.isEmpty()) {
            recipes = recipeRepository.searchByTitle(keyword);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("recipes", recipes);
        return "search";
    }
}
