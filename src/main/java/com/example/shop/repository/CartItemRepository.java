package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shop.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	@Query("SELECT ci FROM CartItem ci JOIN ci.productEntity p JOIN ci.cart c "
			+ "WHERE p.id = :pid" + " AND c.id = :cid")
	CartItem findByProId(@Param("pid") int pid , @Param("cid") int cid);
}
