package com.example.shop.entity;

import java.util.List;

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
@Table(name = "Product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	private int id;
	@Column(name = "p_name")
	private String name;
	@Column(name = "p_img")
	private String img;
	@Column(name = "p_price")
	private double price;
	@Column(name = "p_description")
	private String description;
	@Column(name = "p_quantity")
	private double quantity;
	@ManyToOne
	@JoinColumn(name = "c_id")
	private CategoryEntity categoryEntity;
	@OneToMany(mappedBy = "productEntity")
	List<CartItem> cartIteams;
}
