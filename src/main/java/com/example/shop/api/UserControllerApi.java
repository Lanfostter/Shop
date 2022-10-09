package com.example.shop.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.dto.UserDTO;
import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserControllerApi {
	@Autowired
	UserServiceImpl userServiceImpl;

	@PostMapping("/add")
	public UserDTO addUser(@RequestBody UserDTO userDTO) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userServiceImpl.create(userDTO);
		return userDTO;
	}

	@GetMapping("/list")
	public List<UserDTO> getAll() {
		return userServiceImpl.getListUser();
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam("id") int id) {
		userServiceImpl.delete(id);
	}

	// @PutMapping("/update")
	// public void update(@RequestBody @Validated UserEntity userEntity) {
	// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	// UserEntity oldOne = userRepository.getById(userEntity.getId());
	// oldOne.setName(userEntity.getName());
	// oldOne.setUsername(userEntity.getUsername());
	// oldOne.setPassword(passwordEncoder.encode(userEntity.getPassword()));
	// oldOne.setRoles(userEntity.getRoles());
	// userRepository.save(oldOne);
	// }

	// @GetMapping("search")
	// public UserEntity getUserEntity(@RequestParam("name") String name) {
	// return userRepository.findByUsername(name);
	// }
}
