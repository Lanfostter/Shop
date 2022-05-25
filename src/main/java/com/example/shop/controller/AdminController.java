package com.example.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.shop.repository.ProductRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ProductRepository productRepository;
}
