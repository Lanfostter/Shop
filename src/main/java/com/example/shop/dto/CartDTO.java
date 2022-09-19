package com.example.shop.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class CartDTO {
    private int id;
    private Date buyDate;
    private boolean payup;
    private double totalprice;
    private UserDTO userDTO;
    private List<CartItemDTO> cartItemDTOs;
}
