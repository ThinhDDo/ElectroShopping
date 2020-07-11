package com.spring.exam.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.PageableProductService;
import com.spring.exam.sys.service.ProductCategoryService;
import com.spring.exam.sys.service.UserService;

@Controller
public class StoreController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private PageableProductService pageableService;
	
	@GetMapping(value="/store")
	public String openStore(@RequestParam(value = "currentPage", defaultValue = "1", required = true) String currentPage,
							@RequestParam(value = "option", defaultValue = "", required = true) String option,
							Model model, 
							Authentication auth) {
		
		int cp = Integer.parseInt(currentPage);
		
		if(option.equals("next")) {
			currentPage = (cp + 1) + "";
			cp += 1;
		}
		if(option.equals("back")) {
			currentPage = (cp - 1) + "";
			cp -= 1;
		}
		
		List<ProductCategory> products = productCategoryService.selectProductsByPage((cp-1) * 6);	
		
		UserInfo userProfile = null;
		if(auth!=null) {
			if(auth.isAuthenticated()) {
				User loginUser = (User) auth.getPrincipal();
				userProfile = userService.selectUserByName(loginUser.getUsername());
			}
		}
		
		// Other data
		model.addAttribute("user", userProfile);
		model.addAttribute("pageSize", pageableService.getPageSize());
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("products", products);
		model.addAttribute("productsAvailable", productCategoryService.selectProducts().size());
		
		return "store";
	}	
}
