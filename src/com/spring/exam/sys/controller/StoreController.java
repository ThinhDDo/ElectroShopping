package com.spring.exam.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.service.ProductCategoryService;

@Controller
public class StoreController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping(value="/store")
	public String openStore(Model model) {
		
		List<ProductCategory> products = productCategoryService.selectProducts();
		model.addAttribute("products", products);
		
		return "store";
	}
}
