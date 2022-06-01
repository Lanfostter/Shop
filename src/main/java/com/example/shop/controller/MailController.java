package com.example.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.shop.service.MailService;

@Controller
public class MailController {
	@Autowired
	MailService mailService;

	/*
	 * @GetMapping("/sendmail") public String sendMail() {
	 * mailService.sendEmail("fostter2@gmail.com", "test", "test"); return "index";
	 * }
	 */
}
