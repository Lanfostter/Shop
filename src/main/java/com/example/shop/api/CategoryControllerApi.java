package com.example.shop.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.shop.entity.CategoryEntity;
import com.example.shop.repository.CategoryRepository;

@RestController
@RequestMapping("/api/category")
public class CategoryControllerApi {
	@Autowired
	CategoryRepository categoryRepository;

	@PostMapping("/add")
	// @ResponseBody nếu ko sử dụng restcontroller
	public CategoryEntity addproduct(@RequestParam("imagefile") MultipartFile imagefile,
			@ModelAttribute("category") @Validated CategoryEntity categoryEntity) throws IOException {
		String originalFilename = imagefile.getOriginalFilename();
		int lastIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(lastIndex);
		String imagineFilename = System.currentTimeMillis() + ext;
		File newfile = new File("C:\\Users\\fostt\\eclipse-workspace\\shop\\src\\main\\resources\\static\\img\\" + imagineFilename);
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
		return categoryEntity;
	}

	@GetMapping("/list")
	public List<CategoryEntity> getAll() {
		return categoryRepository.findAll();
	}
	/*
	 * @GetMapping("/list") public List<ProductEntity> productEntities() { return
	 * productRepository.findAll(); }
	 */
}
