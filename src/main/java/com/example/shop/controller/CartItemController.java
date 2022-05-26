package com.example.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shop.entity.Cart;
import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.ProductRepository;

@Controller
public class CartItemController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartRepository cartRepository;

	@GetMapping("/cartitem")
	public String item(@RequestParam("id") int id, Model model) {
		model.addAttribute("saleproduct", productRepository.findById(id).get());
		model.addAttribute("cart", new Cart());
		return "item";
	}

	@PostMapping("cartitem")
	public String item(@ModelAttribute("saleproduct") ProductEntity productEntity, 
			@ModelAttribute("cart") Cart cart) {
		cartRepository.save(cart);
		return "cart";
	}
}
