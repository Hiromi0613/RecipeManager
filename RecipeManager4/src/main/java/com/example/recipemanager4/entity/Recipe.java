package com.example.recipemanager4.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private int id;
    private String title;
    private String description;
    private int categoryId;
    private int userId;  // 忘れていたら追加
    
    private LocalDateTime createdAt; // ★追加
    private LocalDateTime updatedAt; // ★追加

}
