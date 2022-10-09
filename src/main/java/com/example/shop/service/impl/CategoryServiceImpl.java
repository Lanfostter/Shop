package com.example.shop.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.dto.CategoryDTO;
import com.example.shop.entity.CategoryEntity;
import com.example.shop.service.CategoryServiceDAO;

@Service
public class CategoryServiceImpl implements CategoryServiceDAO{
    @Autowired
    ModelMapper modelMapper;
    @Override
    public void create(CategoryDTO categoryDTO) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean deletebyID(int id) {
        // TODO Auto-generated method stub
        return false;
    }
    public CategoryDTO convertToDTO(CategoryEntity categoryEntity){
        CategoryDTO categoryDTO = modelMapper.map(categoryEntity, CategoryDTO.class);
        return categoryDTO;
    }
    public CategoryEntity convertToEntity(CategoryDTO categoryDTO){
        CategoryEntity categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);
        return categoryEntity;
    }
}
