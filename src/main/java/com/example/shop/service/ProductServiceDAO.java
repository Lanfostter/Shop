package com.example.shop.service;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.summary.Product;

import com.example.shop.dto.ProductDTO;

public interface ProductServiceDAO {
    void create(ProductDTO productDTO);
    void update(ProductDTO productDTO);
    void delete(int id);
    List<ProductDTO> getList();
    List<ProductDTO> getbyName(String name);
    ProductDTO getbyId(int id);
}
