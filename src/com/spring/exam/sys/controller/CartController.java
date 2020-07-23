package com.spring.exam.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.exam.sys.model.Cart;
import com.spring.exam.sys.model.Category;
import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.CartServiceImpl;
import com.spring.exam.sys.service.ProductCategoryServiceImpl;
import com.spring.exam.sys.service.UserService;

@Controller
@SessionAttributes(names = {"user", "categories", "companyInfo", "mycart", "totalPrices"})
public class CartController {
	
	@Autowired
	private ProductCategoryServiceImpl productCategoryService;
	
	@Autowired
	private CartServiceImpl cartService;
	
	@Autowired 
	private UserService userService;
	
	/**
	 * Show all products chosen by the user
	 * Login not Required
	 * @return
	 */
	@GetMapping(value="/cart")
	public String showCart(@CookieValue(name="cart", defaultValue="", required=false) String cookie,
						   Model model,
						   Authentication auth) {
		model.addAttribute("category", new Category()); // For searching
		
		// Get User information
		UserInfo userInfo = null;
		if(auth.isAuthenticated()) {
			User loginUser = (User) auth.getPrincipal();
			userInfo = userService.selectUserByName(loginUser.getUsername());
			model.addAttribute("user", userInfo);
		}
		
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
		model.addAttribute("totalPrices", totalPrices);
		return "cart";
	}
	
	/**
	 * Check out and Purchase all the items chosen by user
	 * Login Required
	 * @return
	 */
	@GetMapping(value="/checkout")
	public String checkOut(@CookieValue(name="cart", defaultValue="", required=false) String cookie,
						   Model model,
						   Authentication auth) {
		// Create essential model
		model.addAttribute("category", new Category()); // For searching
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
		model.addAttribute("totalPrices", totalPrices);
		
		// Get User information
		UserInfo userInfo = null;
		if(auth.isAuthenticated()) {
			User loginUser = (User) auth.getPrincipal();
			userInfo = userService.selectUserByName(loginUser.getUsername());
			model.addAttribute("user", userInfo);
		}
		
		Cart cartInfo = new Cart();
		cartInfo.getShipment().setAddress(userInfo.getAddress());
		model.addAttribute("cartInfo", new Cart());
		
		return "checkout";
	}
	
	/**
	 * Show history of user's ordered
	 * @param cookie
	 * @return
	 */
	@GetMapping(value="/history")
	public String historyCart(@CookieValue(name="cart", defaultValue="", required=true) String cookie,
							  @SessionAttribute("user") UserInfo user,
							  Model model) {
		List<Cart> invoices = cartService.selectCartsByUsername(user.getUsername());
		model.addAttribute("invoices", invoices);
		// Create essential model
		model.addAttribute("category", new Category()); // For searching
		return "history";
	}
	
	/**
	 * Place order and purchase
	 * @param cookie
	 * @return
	 */
	@PostMapping(value="/purchase")
	public String placeOrder(@SessionAttribute(name="user", required=true) UserInfo user,
							 @SessionAttribute(name="mycart", required=true) List<ProductCategory> products,
			 				 @CookieValue(name="cart", defaultValue="", required=true) String cookie,
							 @ModelAttribute(name = "cartInfo") Cart cart,
							 HttpServletResponse response) {
		
		// If cookie is empty than redirect to Cart page
		if(cookie.equals("")) {
			return "redirect:/cart";
		}
		
		// Set user to Cart
		cart.setUser(user.getUsername());
		cart.setProducts(products);
		System.out.println("cart Info:" + cart.toString());		
		
		// Insert into database
		cartService.insertNewCart(cart);
		
		// Reset Cookie
		response.addCookie(new Cookie("cart", ""));
		
		return "redirect:/history";
	}
	
	
	/**
	 * Delete a product from cart list
	 * Login not required
	 * @param delItem
	 * @param cookie
	 * @param response
	 * @return
	 */
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
				
				cookie = cookie.replaceAll(items[itemIdx], "")
		                .replaceAll("--", "-")
		                .replaceAll("[-]$", "")
		                .replaceAll("^[-]", "");
				response.addCookie(new Cookie("cart", cookie));
				break;
			}
		}
		
		return "redirect:/cart";
	}
	
	
}
