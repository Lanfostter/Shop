package com.example.shop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.ProductRepository;

@RestController
@RequestMapping("/api/product")
public class ProductControllerApi {
	@Autowired
	ProductRepository productRepository;

	@PostMapping("/add")
	//@ResponseBody nếu ko sử dụng restcontroller
	public ProductEntity addproduct(@ModelAttribute("product") ProductEntity productEntity) {
		productRepository.save(productEntity);
		return productEntity;
	}

	@GetMapping("/list")
	public List<ProductEntity> productEntities() {
		return productRepository.findAll();
	}
}
