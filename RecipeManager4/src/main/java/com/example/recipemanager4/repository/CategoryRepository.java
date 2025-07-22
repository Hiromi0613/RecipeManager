package com.example.recipemanager4.repository;

import java.util.List;

import com.example.recipemanager4.entity.Category;

public interface CategoryRepository {
    List<Category> findAll();
    Category findById(int id);
}
