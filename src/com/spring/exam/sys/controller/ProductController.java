package com.spring.exam.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.exam.sys.model.Category;
import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.service.ProductCategoryService;

@Controller
@SessionAttributes(names = {"user", "categories", "companyInfo", "qtyHeader"})
public class ProductController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping(value="/product")
	public String showDetails(@RequestParam(name = "pid", required = true, defaultValue = "") String pid,
							  Model model) {
		
		if(!pid.equals("")) {
			ProductCategory product = productCategoryService.selectProductById(Integer.parseInt(pid));
			model.addAttribute("productDetail", product);
		}
		
		model.addAttribute("category", new Category());
		return "product";
	}
}
