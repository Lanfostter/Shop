package com.example.shop;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shop.entity.ProductEntity;
import com.example.shop.repository.CategoryRepository;
import com.example.shop.repository.ProductRepository;

@Controller
public class Home {
	private static Logger logger = LoggerFactory.getLogger(Home.class);

	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	/*
	 * @GetMapping("/home") public String home(Model model, HttpServletRequest
	 * request, RedirectAttributes redirectAttributes) {
	 * request.getSession().setAttribute("productList", null); if
	 * (model.asMap().get("success") != null) {
	 * redirectAttributes.addFlashAttribute("success",
	 * model.asMap().get("success").toString()); } return "redirect:/page/1"; }
	 */

	@GetMapping(value = "/download")
	public void download(HttpServletResponse response, @RequestParam("image") String image) {
		final String uploadFolder = "C:\\Users\\fostt\\eclipse-workspace\\shop\\src\\main\\resources\\static\\img\\";// tao
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

	/*
	 * @GetMapping("/page/{page}") public String showTicketPage(HttpServletRequest
	 * request, @PathVariable int page, Model model,
	 * 
	 * @RequestParam(name = "name", required = false) String name) {
	 * PagedListHolder<?> pagedListHolder = (PagedListHolder<?>)
	 * request.getSession().getAttribute("productList"); int pagesize = 5;
	 * List<ProductEntity> list; if (name == null && name.isEmpty()) { list =
	 * (List<ProductEntity>) productRepository.findAll(); } else { list =
	 * (List<ProductEntity>) productRepository.findByName(name); } if
	 * (pagedListHolder == null) { pagedListHolder = new PagedListHolder<>(list);
	 * pagedListHolder.setPageSize(pagesize); } else { final int goTopage = page -
	 * 1; if (goTopage <= pagedListHolder.getPageCount() && goTopage >= 0) {
	 * pagedListHolder.setPage(goTopage); } }
	 * request.getSession().setAttribute("productList", pagedListHolder); int
	 * current = pagedListHolder.getPage() + 1; int begin = Math.max(1, current -
	 * list.size()); int end = Math.min(begin + 5, pagedListHolder.getPageCount());
	 * int totalPageCount = pagedListHolder.getPageCount();
	 * model.addAttribute("currentIndex", current); model.addAttribute("products",
	 * pagedListHolder); model.addAttribute("beginIndex", begin);
	 * model.addAttribute("endIndex", end); model.addAttribute("name", name == null
	 * ? "" : name); model.addAttribute("category", categoryRepository.findAll());
	 * model.addAttribute("totalPageCount", totalPageCount); return "index"; }
	 */
	/*
	 * @PutMapping("/update") public
	 */
	@GetMapping("/home")
	public String search(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "id", required = false) Integer id,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size,
			@RequestParam(name = "sortBy", required = false) String sortBy) {
		if (size == null)
			size = 3;// Số lượng trang
		if (page == null)
			page = 0;// Trang hiện tại

		Sort sort = Sort.by("id").ascending();

		/*
		 * if (sortBy != null && sortBy.equals("name")) { sort =
		 * Sort.by("name").ascending(); } else if (sortBy != null &&
		 * sortBy.equals("date")) { sort = Sort.by("createdAt").ascending(); }
		 */

		Pageable pageable = PageRequest.of(page, size, sort);

		if (name != null && !name.isEmpty()) {
			Page<ProductEntity> pageProduct = productRepository.search("%" + name + "%", pageable);

			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		} else if (id != null) {
			ProductEntity productEntity = productRepository.findById(id).orElse(null);
			if (productEntity != null) {
				model.addAttribute("list", Arrays.asList(productEntity));
			} else
				// log
				logger.info("Id not found");

			model.addAttribute("totalPage", 0);
		} else {
			Page<ProductEntity> pageProduct = productRepository.findAll(pageable);

			model.addAttribute("list", pageProduct.toList());
			model.addAttribute("totalPage", pageProduct.getTotalPages());
		}

		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("name", name == null ? "" : name);
		model.addAttribute("id", id == null ? "" : id);
		model.addAttribute("sortBy", sortBy == null ? "" : sortBy);
		model.addAttribute("category", categoryRepository.findAll());
		return "index";
	}
}