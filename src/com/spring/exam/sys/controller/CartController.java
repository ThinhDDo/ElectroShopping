package com.spring.exam.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.exam.sys.model.Category;
import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.service.ProductCategoryServiceImpl;

@Controller
@SessionAttributes(names = {"user", "categories", "companyInfo", "qtyHeader"})
public class CartController {
	
	@Autowired
	private ProductCategoryServiceImpl productCategoryService;
	
	/**
	 * Show all products chosen by the user
	 * Login not Required
	 * @return
	 */
	@GetMapping(value="/cart")
	public String showCart(@CookieValue(name="cart", defaultValue="", required=false) String cookie,
						   Model model) {
		model.addAttribute("category", new Category()); // For searching
		
		List<ProductCategory> products = new ArrayList<>();
		if(!cookie.equals("")) {
			String[] cart = cookie.split("-"); // id:quantity
			int numberOfProducts = 0; // The amount of products that user bought
			double totalPrices = 0.0; // Temporary Total Prices (before sale)
			
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
			
			// Total price 
			
			
			// Add ModelAndView
			model.addAttribute("numberOfProducts", numberOfProducts);
			model.addAttribute("mycart", products);
			model.addAttribute("totalPrices", totalPrices);
		}
		return "cart";
	}
	
	/**
	 * Check out and Purchase all the items chosen by user
	 * Login Required
	 * @return
	 */
	@GetMapping(value="/checkout")
	public String checkOut(Model model) {
		model.addAttribute("category", new Category()); // For searching
		return "checkout";
	}
	
	@GetMapping(value="/delete")
	public String deleteItem(@RequestParam(value="item", defaultValue="", required=true) String delItem,
							 @CookieValue(name="cart", defaultValue="", required=true) String cookie,
							 HttpServletResponse response) {
		
		// Delete item from cookie
		String[] items = cookie.split("-");
		for(int itemIdx=0; itemIdx < items.length; itemIdx++) {
			String[] item = items[itemIdx].split(":");
			// id: item[0]
			// quantity: item[1]
			if(delItem.equals(item[0])) {
				
				cookie = cookie.replaceAll(item[0], "")
		                .replaceAll("--", "-")
		                .replaceAll("[-]$", "")
		                .replaceAll("^[-]", "");
				response.addCookie(new Cookie("cart", cookie));
			}
		}
		
		return "redirect:/cart";
	}
}
