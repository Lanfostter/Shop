package com.example.shop.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String img;
    private double price;
    private double quantity;
    private String description;
}
