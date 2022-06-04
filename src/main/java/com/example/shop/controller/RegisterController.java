package com.example.shop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shop.dto.MailDTO;
import com.example.shop.entity.UserEntity;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.MailService;

@Controller
public class RegisterController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	MailService mailService;

	@GetMapping("/register")
	public String userRegister(Model model) {
		model.addAttribute("user", new UserEntity());
		return "register";
	}

	@PostMapping("/register")
	public String userRegister(@ModelAttribute("user") UserEntity userEntity, @RequestParam("bdate") String date)
			throws ParseException {
		List<String> list = new ArrayList<>();
		list.add("ROLE_MEMBER");
		userEntity.setRoles(list);
		MailDTO mailDTO = new MailDTO();
		mailDTO.setContent("Bạn đã đăng ký thành công");
		mailDTO.setSubject("Bạn đã đăng ký thành công");
		mailDTO.setTo(userEntity.getEmail());
		mailService.sendEmail(mailDTO, userEntity.getUsername(), userEntity.getPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		userEntity.setBirthday(simpleDateFormat.parse(date));
		userRepository.save(userEntity);
		return "redirect:/login";
	}
}
