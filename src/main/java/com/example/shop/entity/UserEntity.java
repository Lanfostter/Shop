package com.example.shop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "User")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "u_id")
	private int id;
	@Column(name = "u_name")
	private String name;
	@Column(name = "u_username")
	private String username;
	@Column(name = "u_password")
	private String password;
	@Column(name = "u_role")
	private String role;
	@OneToMany(mappedBy = "userEntity")
	private List<Cart> carts;
}
