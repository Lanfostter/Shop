package com.example.shop.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private int id;
    private String name;
    private String img;
    private double price;
    private double quantity;
    private String description;
}
