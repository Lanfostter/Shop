package com.example.shop.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shop.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByUsername(String username);

	@Query("SELECT u FROM UserEntity u WHERE u.name LIKE :u")
	ArrayList<UserEntity> findByName(@Param("u") String name);

	@Query("SELECT u FROM UserEntity u ORDER BY name")
	ArrayList<UserEntity> findAll();
}
