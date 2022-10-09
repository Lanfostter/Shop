package com.example.shop.service;

import com.example.shop.dto.CategoryDTO;

public interface CategoryServiceDAO {
    void create(CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);
    boolean deletebyID (int id);
}
