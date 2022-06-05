package com.example.shop.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.entity.CategoryEntity;
import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;

@Controller
/*
 * @PreAuthorize("hasAuthority('ROLE_ADMIN')")
 */@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/category/add")
	public String addCategory(Model model) {
		model.addAttribute("category", new CategoryEntity());
		return "admin/add_category";
	}

	@PostMapping("/category/add")
	public String addCategory(@ModelAttribute("category") CategoryEntity categoryEntity,
			@RequestParam("imagefile") MultipartFile imagefile) {
		String originalFilename = imagefile.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(lastIndex);
		String imagineFilename = System.currentTimeMillis() + ext;
		File newfile = new File(
				"C:\\Users\\fostt\\eclipse-workspace\\shop\\src\\main\\resources\\static\\img\\" + imagineFilename);
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(imagefile.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		categoryEntity.setImg(imagineFilename);
		categoryRepository.save(categoryEntity);

		return "redirect:/admin/category/list";
	}

	@GetMapping("/category/list")
	public String listCategory(Model model) {
		model.addAttribute("category", categoryRepository.findAll());
		return "admin/list_category";
	}

	@GetMapping("/category/delete")
	public String deleteCategory(@RequestParam("id") int id) {
		categoryRepository.deleteById(id);
		return "redirect:/admin/category/list";
	}

	@GetMapping("/category/update")
	public String updateCategory(@RequestParam("id") int id, Model model) {
		model.addAttribute("newcategory", categoryRepository.findById(id));
		return "admin/update_category";
	}

	@PostMapping("/category/update")
	public String updateCategory(@ModelAttribute("newcategory") CategoryEntity categoryEntity,
			@RequestParam("imagefile") MultipartFile imagefile) {
		String originalFilename = imagefile.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(lastIndex);
		String imagineFilename = System.currentTimeMillis() + ext;
		File newfile = new File(
				"C:\\Users\\fostt\\eclipse-workspace\\shop\\src\\main\\resources\\static\\img\\" + imagineFilename);
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(imagefile.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		categoryEntity.setImg(imagineFilename);
		categoryRepository.save(categoryEntity);
		return "redirect:/admin/category/list";
	}

	@GetMapping("/product/list")
	public String listProduct(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "admin/list_product";

	}

	@GetMapping("/product/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new ProductEntity());
		model.addAttribute("allcategory", categoryRepository.findAll());
		return "admin/add_product";
	}

	@PostMapping("/product/add")
	public String addProduct(@ModelAttribute("product") ProductEntity productEntity,
			@RequestParam("imagefile") MultipartFile imagefile) {
		String originalFilename = imagefile.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(lastIndex);
		String imagineFilename = System.currentTimeMillis() + ext;
		File newfile = new File(
				"C:\\Users\\fostt\\eclipse-workspace\\shop\\src\\main\\resources\\static\\img\\" + imagineFilename);
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(newfile);
			fileOutputStream.write(imagefile.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		productEntity.setImg(imagineFilename);
		productRepository.save(productEntity);
		return "admin/add_product";
	}
}
