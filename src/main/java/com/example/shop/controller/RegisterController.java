package com.example.shop.controller;

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
	public String userRegister(@ModelAttribute("user") UserEntity userEntity) {
		List<String> list = new ArrayList<>();
		list.add("ROLE_MEMBER");
		userEntity.setRoles(list);
		try {
			MailDTO mailDTO = new MailDTO();
			mailDTO.setTo(userEntity.getEmail());
			mailDTO.setSubject(Const.SEND_MAIL_SUBJECT.CLIENT_REGISTER);

			Map<String, Object> props = new HashMap<>();
			props.put("name", userEntity.getName());
			props.put("username", userEntity.getUsername());
			props.put("password", userEntity.getPassword());
			mailDTO.setProps(props);
			String TEMPLATE_FILE_NAME = "";
			mailService.sendHtmlMail(mailDTO, Const.TEMPLATE_FILE_NAME.CLIENT_REGISTER);
			mailService.sendEmail(mailDTO, "registermail");
		} catch (MessagingException exp) {
			exp.printStackTrace();
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userRepository.save(userEntity);
		return "redirect:/login";
	}
}
