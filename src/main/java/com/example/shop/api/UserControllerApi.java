package com.example.shop.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
