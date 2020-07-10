package com.spring.exam.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.exam.sys.model.Product;
import com.spring.exam.sys.service.ProductService;

@Controller
public class StoreController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/store")
	public String openStore(Model model) {
		
		List<Product> products = productService.selectProducts();
		model.addAttribute("products", products);
		
		return "store";
	}
}
