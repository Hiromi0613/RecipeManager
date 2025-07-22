package com.example.recipemanager4.repository;

import java.util.List;

import com.example.recipemanager4.entity.Recipe;

public interface RecipeRepository {
    void save(Recipe recipe);
    List<Recipe> findAll();
    Recipe findById(int id); // ← 追加
    List<Recipe> findByUserId(int userId);
    void update(Recipe recipe);
    void deleteById(int id); // ← 追加
    List<Recipe> searchByTitle(String keyword);
    List<Recipe> findByCategoryId(int categoryId);
}
