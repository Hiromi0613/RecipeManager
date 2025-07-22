package com.example.recipemanager4.repository;

import java.util.List;
import java.util.Optional;

import com.example.recipemanager4.entity.User;

public interface UserRepository {
    List<User> findAll();
    void save(User user);
    User findByEmailAndPassword(String email, String password);
    Optional<User> findByEmail(String email);

}
