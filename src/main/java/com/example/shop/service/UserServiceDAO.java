package com.example.shop.service;

import java.util.List;

import com.example.shop.dto.UserDTO;

public interface UserServiceDAO {
    void create(UserDTO userDTO);
    void update(UserDTO userDTO);
    boolean delete(int id);
    List<UserDTO> getListUser();
}
