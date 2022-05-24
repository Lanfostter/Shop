package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shop.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByUsername(String username);
}
