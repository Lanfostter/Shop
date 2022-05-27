package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shop.entity.CartIteam;

public interface CartItemRepository extends JpaRepository<CartIteam, Integer>{

}
