package com.example.shop.entity;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@Column(name = "u_email")
	private String email;
	@Column(name = "u_username")
	private String username;
	@Column(name = "u_password")
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "u_role")
	@CollectionTable(name = "user_role", 
		joinColumns = @JoinColumn(name = "u_id"))
	private List<String> roles;
	@OneToMany(mappedBy = "userEntity")
	private List<Cart> carts;
}
