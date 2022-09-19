package com.example.shop.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private CartDTO cartDTO;
    private ProductDTO productDTO;
    private int quantity;
}
