package com.example.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shop.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{
	@Query("SELECT c FROM Cart c JOIN c.userEntity u "
			+ "WHERE u.username = :username")
	Cart findByUserEntity(@Param("username") String name);
	}
