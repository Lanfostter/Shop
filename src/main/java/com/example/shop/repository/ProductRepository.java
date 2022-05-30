package com.example.shop.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shop.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	/*
	 * List<ProductEntity> findByName(String name);
	 */
	@Query("SELECT p FROM ProductEntity p WHERE p.name LIKE :p")
	Page<ProductEntity> search(@Param("p") String name, Pageable pageable);
	@Query("SELECT p FROM ProductEntity p JOIN p.categoryEntity c WHERE c.name LIKE :cname")
	Page<ProductEntity> searchByCategory(@Param("cname") String name, Pageable pageable);
}
