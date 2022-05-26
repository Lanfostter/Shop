package com.example.shop.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.ProductRepository;

@Controller
public class CartItemController {
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/cartitem")
	public String item(@RequestParam("id") int id, Model model) {
		model.addAttribute("saleproduct", productRepository.findById(id).get());
		return "item";
	}

	@PostMapping("cartitem")
	public String item(@ModelAttribute("saleproduct") ProductEntity productEntity) {
		return "cart";
	}
}
