package com.spring.exam.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.exam.sys.model.Category;
import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.ProductCategoryService;
import com.spring.exam.sys.service.UserService;

@Controller
@SessionAttributes(names = {"user", "categories"})
public class HomeController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	private static Authentication authentication;
	private static UserInfo userProfile;
	
	/**
	 * Welcome page, Profile page
	 * @param model
	 * @return
	 */
	@GetMapping(value= {"/","/index"})
	public String home(Model model, 
					   Authentication auth) {
		
		userProfile = null;
		if(auth!=null) {
			authentication = auth;
			if(authentication.isAuthenticated()) {
				User loginUser = (User) authentication.getPrincipal();
				userProfile = userService.selectUserByName(loginUser.getUsername());
			}
		}
		
		model.addAttribute("companyInfo", userService.selectUserByName("admin")); // Shop info
		model.addAttribute("user", userProfile); // User info
		model.addAttribute("category", new Category());
		model.addAttribute("categories", productCategoryService.selectCategories());
		return "index";
	}
	
	@PostMapping(value="/search")
	public String searchProduct(@SessionAttribute("categories") List<Category> categories,
								@ModelAttribute("category") Category category, 
								@RequestParam(name = "searchProduct", required = false, defaultValue = "") String searchProduct, 
								Model model) {
		
		if(searchProduct.equals("")) {
			return "redirect:/";
		}
		
		if(authentication!=null && authentication.isAuthenticated()) {
			model.addAttribute("user", userProfile);
		}
		
		model.addAttribute("searchResults", productCategoryService.selectProductsByName(searchProduct, category.getCategory_id()));
		
		return "search";
	}
}
