package com.example.shop.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


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
	@NotEmpty
	@Column(name = "u_username")
	private String username;
	@Size(min = 5, message = "Mật khẩu tối thiểu 5 kí tự")
	@NotEmpty
	@Column(name = "u_password")
	private String password;
	@Column(name = "u_gender")
	private String gender;
	@Column(name = "u_birthday")
	private Date birthday;
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "u_role")
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "u_id"))
	private List<String> roles;
	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.REMOVE)
	private List<Cart> carts;

	@Override
	public boolean equals(Object object) {
		if (object instanceof UserEntity) {
			UserEntity u = (UserEntity) object;
			if (u.getUsername().equals(this.username)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
