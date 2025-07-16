package com.example.recipemanager3.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.recipemanager3.entity.Recipe;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

    private final JdbcTemplate jdbcTemplate;

    public RecipeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Recipe recipe) {
        jdbcTemplate.update(
            "INSERT INTO recipe (title, description, category_id, user_id) VALUES (?, ?, ?, ?)",
            recipe.getTitle(),
            recipe.getDescription(),
            recipe.getCategoryId(),
            recipe.getUserId() // ← 追加
        );
    }
    
    @Override
    public List<Recipe> findAll() {
        String sql = "SELECT * FROM recipe ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new RecipeRowMapper());
    }

    @Override
    public Recipe findById(int id) {
        String sql = "SELECT * FROM recipe WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new RecipeRowMapper(), id);
    }

    @Override
    public List<Recipe> findByUserId(int userId) {
        String sql = "SELECT * FROM recipe WHERE user_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new RecipeRowMapper(), userId);
    }

    @Override
    public void update(Recipe recipe) {
        String sql = "UPDATE recipe SET title = ?, description = ?, category_id = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ? AND user_id = ?";
        jdbcTemplate.update(sql,
            recipe.getTitle(),
            recipe.getDescription(),
            recipe.getCategoryId(),
            recipe.getId(),
            recipe.getUserId()
        );
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM recipe WHERE id = ?", id);
    }

    @Override
    public List<Recipe> searchByTitle(String keyword) {
        String sql = "SELECT * FROM recipe WHERE title LIKE ? ORDER BY created_at DESC";
        String pattern = "%" + keyword + "%";
        return jdbcTemplate.query(sql, new RecipeRowMapper(), pattern);
    }

    @Override
    public List<Recipe> findByCategoryId(int categoryId) {
        String sql = "SELECT * FROM recipe WHERE category_id = ? ORDER BY created_at DESC";
        return jdbcTemplate.query(sql, new RecipeRowMapper(), categoryId);
    }

}
