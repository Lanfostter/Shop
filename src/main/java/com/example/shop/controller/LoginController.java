package com.example.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	// trang login
	@GetMapping("/login")
	public String login(HttpServletRequest request, @RequestParam(name = "err", required = false) String error) {
		if (error != null) {
			request.setAttribute("e", "Tài Khoản hoặc mật khẩu không đúng");
		}
		return "login";
	}
}
