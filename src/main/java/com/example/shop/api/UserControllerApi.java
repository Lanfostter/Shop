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
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.entity.CategoryEntity;
import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserControllerApi {
	@Autowired
	UserRepository userRepository;

	@PostMapping("/add")
	public UserEntity userEntity(@ModelAttribute("user") @Validated UserEntity userEntity) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userRepository.save(userEntity);
		return userEntity;
	}

	@GetMapping("/list")
	public List<UserEntity> getAll() {
		return userRepository.findAll();
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam("id") int id) {
		userRepository.deleteById(id);
	}

	@PutMapping("/update")
	public void update(@RequestBody @Validated UserEntity userEntity) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		UserEntity oldOne = userRepository.getById(userEntity.getId());
		oldOne.setName(userEntity.getName());
		oldOne.setUsername(userEntity.getUsername());
		oldOne.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		oldOne.setRoles(userEntity.getRoles());
		userRepository.save(oldOne);
	}
	@GetMapping("search")
	public UserEntity getUserEntity(@RequestParam("name") String name ) {
		return userRepository.findByUsername(name);
	}
}
