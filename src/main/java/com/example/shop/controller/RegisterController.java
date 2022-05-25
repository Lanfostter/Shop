package com.example.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;

@Controller
public class RegisterController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/register")
	public String userRegister(Model model) {
		model.addAttribute("user", new UserEntity());
		return "register";
	}

	@PostMapping("/register")
	public String userRegister(@ModelAttribute("user") UserEntity userEntity) {
		List<String> list = new ArrayList<>();
		list.add("ROLE_MEMBER");
		userEntity.setRoles(list);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userRepository.save(userEntity);
		return "redirect:/login";
	}
}
