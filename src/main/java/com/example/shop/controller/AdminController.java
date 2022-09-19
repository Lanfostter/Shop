package com.example.shop.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.dto.MailDTO;
import com.example.shop.dto.UserDTO;
import com.example.shop.entity.CategoryEntity;
import com.example.shop.entity.ProductEntity;
import com.example.shop.entity.UserEntity;
import com.example.shop.repository.CartRepository;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.service.MailService;
import com.example.shop.service.impl.UserServiceImpl;
import java.util.List;

@Controller

@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MailService mailService;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserServiceImpl userServiceImpl;
	@GetMapping("/category/add")
	public String addCategory(Model model) {
		model.addAttribute("category", new CategoryEntity());
		return "admin/add_category";
	}

	// thêm danh mục
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

	// danh sách danh mục
	@GetMapping("/category/list")
	public String listCategory(Model model) {
		model.addAttribute("category", categoryRepository.findAll());
		return "admin/list_category";
	}

	@GetMapping("/category/search")
	public String searchCategory(Model model, @RequestParam("name") String name) {
		model.addAttribute("category", categoryRepository.findByName("%" + name + "%"));
		return "admin/list_category";
	}

	// xóa danh mục
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

	// cập nhật danh mục
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

	// danh sách sản phẩm
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

	// thêm sản phẩm
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
		return "redirect:/admin/product/list";
	}

	@GetMapping("/product/update")
	public String updateProduct(Model model, @RequestParam("id") int id) {
		model.addAttribute("newproduct", productRepository.getById(id));
		model.addAttribute("allcategory", categoryRepository.findAll());
		return "admin/update_product";
	}

	// cập nhật sản phẩm
	@PostMapping("/product/update")
	public String updateProduct(@ModelAttribute("newproduct") ProductEntity productEntity,
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
		return "redirect:/admin/product/list";
	}

	@GetMapping("/product/search")
	public String searchProduct(Model model, @RequestParam("productname") String name) {
		model.addAttribute("products", productRepository.findbyName("%" + name + "%"));
		return "admin/list_product";

	}

	// xóa sản phẩm
	@GetMapping("/product/delete")
	public String deleteProduct(@RequestParam("id") int id, Model model) {
		productRepository.deleteById(id);
		return "redirect:/admin/product/list";

	}

	// danh sách người dùng
	@GetMapping("/user/list")
	public String listUser(Model model) {
		model.addAttribute("users", userServiceImpl.getListUser());
		return "admin/list_user";
	}

	@GetMapping("/user/add")
	public String addUser(Model model) {
		model.addAttribute("user", new UserEntity());
		String role = null;
		model.addAttribute("chooserole", role);
		return "admin/add_user";
	}

	// thêm người dùng
	@PostMapping("/user/add")
	public String addUser(@Valid @ModelAttribute("user") UserDTO userDTO,
			@ModelAttribute("chooserole") String chooserole, @RequestParam("bdate") String date,
			BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			return "admin/add_user";
		}
		List<String> list = new ArrayList<>();
		if (chooserole.equals("true")) {
			list.add("ROLE_ADMIN");
		} else if (chooserole.equals("false")) {
			list.add("ROLE_MEMBER");
		}
		userDTO.setRoles(list);
		MailDTO mailDTO = new MailDTO();
		mailDTO.setContent("Bạn đã đăng ký thành công");
		mailDTO.setSubject("Bạn đã đăng ký thành công");
		mailDTO.setTo(userDTO.getEmail());
		mailService.sendEmail(mailDTO, 	userDTO.getUsername(), userDTO.getPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		userDTO.setBirthday(simpleDateFormat.parse(date));
		userServiceImpl.create(userDTO);
		return "redirect:/admin/user/list";
	}

	@GetMapping("/user/update")
	public String updateUser(Model model, @RequestParam("id") int id) {
		model.addAttribute("newuser", userRepository.findById(id));
		String role = null;
		model.addAttribute("chooserole", role);
		return "admin/update_user";
	}

	// update người dùng
	@PostMapping("/user/update")
	public String updateUser(@Valid @ModelAttribute("newuser") UserEntity userEntity,
			@ModelAttribute("chooserole") String chooserole,
			@RequestParam("bdate") String date,
			BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			return "register";
		}

		List<String> list = new ArrayList<>();
		if (chooserole.equals("true")) {
			list.add("ROLE_ADMIN");
		} else if (chooserole.equals("false")) {
			list.add("ROLE_MEMBER");

		}
		userEntity.setRoles(list);
		MailDTO mailDTO = new MailDTO();
		mailDTO.setContent("Bạn đã đăng ký thành công");
		mailDTO.setSubject("Bạn đã đăng ký thành công");
		mailDTO.setTo(userEntity.getEmail());
		mailService.sendEmail(mailDTO, userEntity.getUsername(), userEntity.getPassword());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		userEntity.setBirthday(simpleDateFormat.parse(date));
		userRepository.save(userEntity);
		return "redirect:/admin/user/list";
	}

	// xoá người dùng
	@GetMapping("/user/delete")
	public String deleteUser(@RequestParam("id") int id) {
		try {
			userServiceImpl.delete(id);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/admin/user/list";
	}

	@GetMapping("/user/search")
	public String searchUser(Model model, @RequestParam("nameuser") String name) {
		System.out.println(name);
		model.addAttribute("users", userRepository.findByName("%" + name + "%"));
		return "admin/list_user";
	}

	@GetMapping("/cart/list")
	public String allCart(Model model) {
		model.addAttribute("allcart", cartRepository.findallCart());
		return "admin/list_cart";
	}

	@GetMapping("/cart/search")
	public String searchCart(Model model, @RequestParam("name") String name) {
		model.addAttribute("allcart", cartRepository.findByUsername("%" + name + "%"));
		return "admin/list_cart";
	}

}
