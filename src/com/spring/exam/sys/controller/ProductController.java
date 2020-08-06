package com.spring.exam.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.exam.sys.model.Category;
import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.service.ProductCategoryService;

@Controller
@SessionAttributes(names = {"user", "categories", "companyInfo", "mycart"})
public class ProductController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping(value="/product")
	public String showDetails(@CookieValue(name="cart", defaultValue="", required=false) String cookie,
							  @RequestParam(name = "pid", required = true, defaultValue = "") int pid,
							  Model model) {
		
		
		ProductCategory detail = productCategoryService.selectProductById(pid);
		model.addAttribute("productDetail", detail);
		
		// Initiate entry data
		List<ProductCategory> products = new ArrayList<>();
		int numberOfProducts = 0; // The amount of products that user bought
		double totalPrices = 0.0; // Temporary Total Prices (before sale)
		
		if(!cookie.equals("")) {
			String[] cart = cookie.split("-"); // id:quantity
			
			
			for(String item : cart) {
				String[] productValue = item.split(":");
				// TODO: Should create new select: selectProductForCartById
				// Also, check if the stock is available
				ProductCategory product = productCategoryService.selectProductById(Integer.parseInt(productValue[0]));
				
				// Get quantity
				int q = Integer.parseInt(productValue[1]);
				numberOfProducts += q;
				product.setQuantity(q);
				
				// Calculate Total Prices
				totalPrices += (q * product.getPrice());
				
				// Add to list
				products.add(product);
			}
		}
		
		// Add ModelAndView
		model.addAttribute("numberOfProducts", numberOfProducts);
		model.addAttribute("mycart", products);
		model.addAttribute("qtyHeader", products.size());
		model.addAttribute("totalPrices", totalPrices);
		model.addAttribute("category", new Category());
		return "product";
	}
	
	/**
	 * Update Cart Status to Main Header
	 * @param cookie
	 * @param pid
	 * @param model
	 * @return
	 */
//	@PostMapping(value="/product")
//	public String changeCartStatus(@CookieValue(name="cart", defaultValue="", required=false) String cookie,
//							  	   @RequestParam(name = "pid", required = true, defaultValue = "") String pid,
//							  	   Model model) {
//		
//		if(!pid.equals("")) {
//			ProductCategory product = productCategoryService.selectProductById(Integer.parseInt(pid));
//			model.addAttribute("productDetail", product);
//		}
//		
//		// Initiate entry data
//		List<ProductCategory> products = new ArrayList<>();
//		int numberOfProducts = 0; // The amount of products that user bought
//		double totalPrices = 0.0; // Temporary Total Prices (before sale)
//		
//		if(!cookie.equals("")) {
//			String[] cart = cookie.split("-"); // id:quantity
//			
//			
//			for(String item : cart) {
//				String[] productValue = item.split(":");
//				// TODO: Should create new select: selectProductForCartById
//				// Also, check if the stock is available
//				ProductCategory product = productCategoryService.selectProductById(Integer.parseInt(productValue[0]));
//				
//				// Get quantity
//				int q = Integer.parseInt(productValue[1]);
//				numberOfProducts += q;
//				product.setQuantity(q);
//				
//				// Calculate Total Prices
//				totalPrices += (q * product.getPrice());
//				
//				// Add to list
//				products.add(product);
//			}
//		}
//		
//		// Add ModelAndView
//		model.addAttribute("numberOfProducts", numberOfProducts);
//		model.addAttribute("mycart", products);
//		model.addAttribute("qtyHeader", products.size());
//		model.addAttribute("totalPrices", totalPrices);
//		model.addAttribute("category", new Category());
//		return "product";
//	}
}
