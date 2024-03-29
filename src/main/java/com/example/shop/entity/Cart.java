package com.example.shop.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_id")
	private int id;
	@Column(name = "c_buydate")
	private Date buyDate;
	@Column(name = "c_payup")
	private Boolean payup;
	@Column(name = "c_totalprice")
	private double totalprice;
	@ManyToOne
	@JoinColumn(name = "u_id")
	private UserEntity userEntity;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
	private List<CartItem> cartIteams;
}
