package com.example.shop.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private int id;
    private CartDTO cartDTO;
    private ProductDTO productDTO;
    private int quantity;
}
