package com.example.shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.dto.UserDTO;
import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.UserServiceDAO;

@Service
public class UserServiceImpl implements UserServiceDAO {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;

    @Override
    public void create(UserDTO userDTO) {
        userRepository.save(convertToEntity(userDTO));
    }

    @Override
    public void update(UserDTO userDTO) {
        userRepository.save(convertToEntity(userDTO));
    }

    @Override
    public boolean delete(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<UserDTO> getListUser() {
        List<UserDTO> list = new ArrayList<UserDTO>();
        for (UserEntity userEntity : userRepository.findAll()) {
            UserDTO userDTO = convertToDTO(userEntity);
           list.add(userDTO);
        }
        return list;
    }

    public UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    public UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        return userEntity;
    }

}
