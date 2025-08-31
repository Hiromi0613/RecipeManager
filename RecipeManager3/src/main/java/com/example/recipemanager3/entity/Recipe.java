package com.example.recipemanager3.entity;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
	
    private int id;
    
    @Size(max = 20, message = "タイトルは20文字以内で入力してください")
    private String title;
    
    private String description;
    
    private int categoryId;
    
    private int userId;  // 忘れていたら追加
    
    private LocalDateTime createdAt; // ★追加
    
    private LocalDateTime updatedAt; // ★追加

}
