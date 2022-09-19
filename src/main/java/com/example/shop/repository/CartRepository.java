package com.example.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.shop.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	@Query("SELECT c FROM Cart c JOIN c.userEntity u " + "WHERE u.username = :username AND c.payup != true")
	Cart findByUserEntity(@Param("username") String name);

	@Query("SELECT c FROM Cart c JOIN c.userEntity u " + "WHERE u.username = :username AND c.payup = false")
	Cart findByUserEntityNotPayUp(@Param("username") String name);

	@Query("SELECT c FROM Cart c JOIN c.userEntity u " + "WHERE u.username = :username AND c.payup = true ORDER BY c.buyDate DESC")
	List<Cart> findbyHistory(@Param("username") String name);

	@Query("SELECT c FROM Cart c JOIN c.userEntity u "
			+ "WHERE u.username = :username AND c.payup = true AND c.id = :cid")
	List<Cart> findbyHistoryTotal(@Param("username") String name, @Param("cid") int id);

	@Query("SELECT c FROM Cart c WHERE c.payup = true ORDER BY buyDate DESC")
	List<Cart> findallCart();

	@Query("SELECT c FROM Cart c JOIN c.userEntity u WHERE u.username LIKE :username")
	List<Cart> findByUsername(@Param("username") String username);
}
