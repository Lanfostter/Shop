package com.example.shop.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Data 
@Table(name = "cartiteam")
public class CartIteam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ci_c_id")
	private Cart cart;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ci_p_id")
	private ProductEntity productEntity;
	@Column(name = "ci_quantity")
	private int quantity;
}
