package com.example.shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDTO {
    private int id;
    private String name;
    private String img;
    private List<ProductDTO> productDTOs;


}
