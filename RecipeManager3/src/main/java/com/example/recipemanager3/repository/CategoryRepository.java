package com.example.recipemanager3.repository;

import java.util.List;

import com.example.recipemanager3.entity.Category;

public interface CategoryRepository {
    List<Category> findAll();
    Category findById(int id);
}
