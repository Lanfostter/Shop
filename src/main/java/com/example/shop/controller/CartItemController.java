package com.example.shop.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shop.entity.Cart;
import com.example.shop.entity.CartIteam;
import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.CartItemRepository;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;

@Controller
@RequestMapping("cart")
public class CartItemController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/item")
	public String item(Model model, @RequestParam("id") int id) {
		model.addAttribute("saleproduct", productRepository.findById(id).get());
		return "item";
	}

	@GetMapping("/addtocart")
	public String addToCart(Principal principal) {
		Cart cart = new Cart();
		cart.setUserEntity(userRepository.findByUsername(principal.getName()));
		cart.setBuyDate(new Date());
		System.out.println(cart.getBuyDate());
		cartRepository.save(cart);
		return "cart";
	}
}
