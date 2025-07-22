package com.example.recipemanager4.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.recipemanager4.entity.Category;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Category> rowMapper = (rs, rowNum) -> new Category(
            rs.getInt("id"),
            rs.getString("name")
    );

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * FROM category", rowMapper);
    }
    
    @Override
    public Category findById(int id) {
        String sql = "SELECT id, name FROM category WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
            new Category(rs.getInt("id"), rs.getString("name"))
        , id);
    }

}
