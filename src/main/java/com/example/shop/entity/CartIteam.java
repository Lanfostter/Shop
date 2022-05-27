package com.example.shop.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data 
@Table(name = "cartiteam")
public class CartIteam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "c_id")
	private Cart cart;
	@ManyToOne
	@JoinColumn(name = "p_id")
	private ProductEntity productEntity;
	@Column(name = "ci_quantity")
	private int quantity;
}
