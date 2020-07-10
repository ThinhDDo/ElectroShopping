package com.spring.exam.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.ProductCategoryService;
import com.spring.exam.sys.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	/**
	 * Welcome page, Profile page
	 * @param model
	 * @return
	 */
	@GetMapping(value= {"/", "/index"})
	public String profile(Model model, Authentication auth) {
		
		UserInfo userProfile = null;
		if(auth!=null) {
			if(auth.isAuthenticated()) {
				User loginUser = (User) auth.getPrincipal();
				userProfile = userService.selectUserByName(loginUser.getUsername());
			}
		}
		
		// TODO: Get All Categories
		
		model.addAttribute("user", userProfile);
		
		return "index";
	}
	
	@PostMapping(value="/search")
	public String searchProduct(@RequestParam(name = "searchProduct", required = false, defaultValue = "") String searchProduct, 
								Model model) {
		
		if(searchProduct.equals("")) {
			return "redirect:/";
		}
		model.addAttribute("products", productCategoryService.selectProductsByName(searchProduct));
		
		return "search";
	}
}
