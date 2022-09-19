package com.example.shop.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoryDTO {
    private String name;
    private String img;
    private List<ProductDTO> productDTOs;


}
