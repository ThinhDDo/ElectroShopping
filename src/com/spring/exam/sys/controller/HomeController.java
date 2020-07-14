package com.spring.exam.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.ProductCategoryService;
import com.spring.exam.sys.service.UserService;

@Controller
@SessionAttributes("loginUser")
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
				model.addAttribute("loginUser", loginUser);
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
		
		if(authentication!=null && authentication.isAuthenticated()) {
			model.addAttribute("user", userProfile);
		}
		model.addAttribute("products", productCategoryService.selectProductsByName(searchProduct));
		
		return "search";
	}
}
