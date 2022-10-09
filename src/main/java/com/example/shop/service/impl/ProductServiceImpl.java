package com.example.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.dto.ProductDTO;
import com.example.shop.entity.ProductEntity;

import com.example.shop.repository.ProductRepository;
import com.example.shop.service.ProductServiceDAO;
@Service
public class ProductServiceImpl implements ProductServiceDAO {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryServiceImpl categoryServiceImpl;
    @Override
    public void create(ProductDTO productDTO) {
        productRepository.save(convertToEntity(productDTO));
    }

    @Override
    public void update(ProductDTO productDTO) {
        productRepository.save(convertToEntity(productDTO));
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ProductDTO> getList() {
        List<ProductDTO> list = new ArrayList<ProductDTO>();
        for(ProductEntity productEntity : productRepository.findAll()){
            ProductDTO productDTO = convertToDTO(productEntity);
            productDTO.setCategoryDTO(categoryServiceImpl.convertToDTO(productEntity.getCategoryEntity()));
            list.add(productDTO);
        }
        return list;
    }

    @Override
    public List<ProductDTO> getbyName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public ProductDTO getbyId(int id) {
        return convertToDTO(productRepository.getById(id));
    }

    public ProductDTO convertToDTO(ProductEntity productEntity){
        ProductDTO productDTO = modelMapper.map(productEntity, ProductDTO.class);
        return productDTO;
    }

    public ProductEntity convertToEntity(ProductDTO productDTO) {
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        return productEntity;
    }
}
