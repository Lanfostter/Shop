package com.example.shop.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
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
import com.example.shop.entity.CartItem;
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
	public String addToCart(@RequestParam("id") int id, Model model, Principal principal) {

		model.addAttribute("saleproduct", productRepository.findById(id).get());
		model.addAttribute("cart", cartRepository.findAll());
		if (cartRepository.findByUserEntity(principal.getName()) == null) {
			Cart cart = new Cart();
			cart.setUserEntity(userRepository.findByUsername(principal.getName()));
			cart.setBuyDate(new Date());
			cart.setPayup(false);
			cartRepository.save(cart);
			CartItem cartItem = new CartItem();
			cartItem.setQuantity(1);
			cartItem.setProductEntity(productRepository.findById(id).get());
			cartItem.setCart(cart);
			cartItemRepository.save(cartItem);
		} else {
			Cart cart = cartRepository.findByUserEntity(principal.getName());
			if (cartItemRepository.findByProId(id, cart.getId()) != null) {
				CartItem cartItem = cartItemRepository.findByProId(id, cart.getId());
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItemRepository.save(cartItem);
			} else {
				CartItem cartItem = new CartItem();
				cartItem.setProductEntity(productRepository.findById(id).get());
				cartItem.setQuantity(1);
				cartItem.setCart(cart);
				cartItemRepository.save(cartItem);
			}
		}
		return "redirect:/cart/listcartitem";
	}

	@GetMapping("/listcartitem")
	public String listCartItem(Model model, Principal principal) {
		if (cartRepository.findByUserEntityNotPayUp(principal.getName()) != null) {
			Cart cart = cartRepository.findByUserEntity(principal.getName());
			model.addAttribute("cart", cart);
			model.addAttribute("cartitem", cartItemRepository.findById(cart.getUserEntity().getId()));
			int double1 = 0;
			for (CartItem cartItem : cartRepository.findByUserEntity(principal.getName()).getCartIteams()) {
				double1 += (cartItem.getQuantity() * cartItem.getProductEntity().getPrice());
			}
			model.addAttribute("totalprice", double1);

		}
		model.addAttribute("totalitem", cartItemRepository.numberItemCart(principal.getName()));

		return "cart";
	}

	@PostMapping("/updatecartitem")
	public String updateCartItem(@ModelAttribute("cartitem") CartItem cartItem, Principal principal) {
		cartItemRepository.save(cartItem);
		return "redirect:/cart/listcartitem";

	}

	@PostMapping("/payup")
	public String payup(@ModelAttribute("cart") Cart cart) {
		cart.setBuyDate(new Date());
		cartRepository.save(cart);
		return "redirect:/cart/listcartitem";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {
		cartItemRepository.deleteById(id);
		return "redirect:/cart/listcartitem";
	}
}
