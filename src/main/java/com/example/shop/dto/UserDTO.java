package com.example.shop.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String username;
    private String password;
    private String gender;
    private Date birthday;
    private List<String> roles; 
    
}
