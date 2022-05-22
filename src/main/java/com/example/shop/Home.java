package com.example.shop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shop.repository.ProductRepository;

@Controller
public class Home {
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("product", productRepository.findAll());
		return "index.html";
	}

	@GetMapping(value = "/download")
	public void download(HttpServletResponse response, @RequestParam("image") String image) {
		final String uploadFolder = "C:\\Users\\fostt\\eclipse-workspace\\shop\\src\\main\\resources\\img\\";// tao
																												// thu
																												// muc
																												// luu
																												// anh
		File file = new File(uploadFolder + File.separator + image);
		if (file.exists()) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
