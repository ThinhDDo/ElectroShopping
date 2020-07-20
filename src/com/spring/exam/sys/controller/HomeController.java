package com.spring.exam.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.exam.sys.model.Category;
import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.ProductCategoryService;
import com.spring.exam.sys.service.UserService;

@Controller
@SessionAttributes(names = {"user", "categories", "companyInfo", "qtyHeader"})
public class HomeController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	private static Authentication authentication;
	private static UserInfo userProfile;
	final static Logger logger = Logger.getLogger(HomeController.class);
	
	/**
	 * Welcome page, Profile page
	 * @param model
	 * @return
	 */
	@GetMapping(value="/")
	public String home(@CookieValue(name="cart", defaultValue="", required = true) String cookie,
					   HttpServletResponse response,
					   Model model, 
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
		
		// Extract cart products from cookie
		logger.info("COOKIE: " + cookie);
		if(!cookie.equals("")) {
			String[] cart = cookie.split("-");
			model.addAttribute("qtyHeader", cart.length);
		} else {			
			model.addAttribute("qtyHeader", 0);
		}
		
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
	
	@GetMapping(value="/test")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<ProductCategory> test(@CookieValue(name="cart", defaultValue="", required = false) String cookie,
									Model model) {
		logger.info("MY CART: " + cookie); 
		List<ProductCategory> list = new ArrayList<ProductCategory>();
		ProductCategory p1 = productCategoryService.selectProductById(13);
		ProductCategory p2 = productCategoryService.selectProductById(14);
		list.add(p1);list.add(p2);
		model.addAttribute("test", list);
		model.addAttribute("amount", 1);
		logger.info("PRODUCTS SIZE: " + list.size());
		
		return list;
	}

}
