package com.example.shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private String img;
    private double price;
    private String description;
    private double quantity;
    public CategoryDTO categoryDTO;
    public List<CartItemDTO> cartItemDTOs;
}
