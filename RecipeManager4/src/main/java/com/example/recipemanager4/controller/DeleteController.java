package com.example.recipemanager4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.recipemanager4.repository.RecipeRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeleteController {

    private final RecipeRepository recipeRepository;

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable int id, HttpSession session) {
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        recipeRepository.deleteById(id);
        return "redirect:/myrecipes";
    }
}
