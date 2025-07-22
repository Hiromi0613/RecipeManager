package com.example.recipemanager4.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.recipemanager4.entity.Recipe;

public class RecipeRowMapper implements RowMapper<Recipe> {

    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getInt("id"));
        recipe.setTitle(rs.getString("title"));
        recipe.setDescription(rs.getString("description"));
        recipe.setCategoryId(rs.getInt("category_id"));
        recipe.setUserId(rs.getInt("user_id"));
        recipe.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        recipe.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return recipe;
    }
}
