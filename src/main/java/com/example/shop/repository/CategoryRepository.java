package com.example.shop.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shop.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query("SELECT c FROM CategoryEntity c WHERE c.name LIKE :c")
    ArrayList<CategoryEntity> findByName(@Param("c") String name);
}
