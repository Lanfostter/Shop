package com.example.shop.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.shop.dto.UserDTO;

public interface UserServiceDAO {
    void create(UserDTO userDTO);
    void update(UserDTO userDTO);
    boolean delete(int id);
    List<UserDTO> getListUser();
    List<UserDTO> findbyName(String name);
    UserDTO findbyId(int id);
    void importToDB(List<MultipartFile> files);
}
