package com.spring.exam.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.PageableProductService;
import com.spring.exam.sys.service.ProductCategoryService;
import com.spring.exam.sys.service.UserService;

@Controller
@SessionAttributes("productPages")
public class StoreController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping(value = "/store")
	public String openStore(Model model) {
		model.addAttribute("productPages", null);
		return "redirect:/store/1";
	}
	
	/**
	 * Khi nguoi dung mo trang store, trang web tu dong load trang dau tien
	 * @param currentPage
	 * @param option
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(value="/store/{page}")
	public String openStorePage(@SessionAttribute("productPages") Object currentPagedListHolder,
								@PathVariable("page") int page,
								Model model,
								Authentication auth) {
		PagedListHolder<?> pages = (PagedListHolder<?>) currentPagedListHolder;
		int pageSize = 6;
		List<ProductCategory> products = productCategoryService.selectProducts();
		System.out.println("products size: " + products.size());
		
		if(currentPagedListHolder==null) {
			pages = new PagedListHolder<ProductCategory>(products);
			pages.setPageSize(pageSize);
		} else {
			int goToPage = page - 1;
			if(goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		
		model.addAttribute("productPages", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - products.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/store/page/";
		
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("products", pages);
		model.addAttribute("baseUrl", baseUrl);
		
//		int cp = Integer.parseInt(currentPage);
//		
//		if(option.equals("next")) {
//			currentPage = (cp + 1) + "";
//			cp += 1;
//		}
//		if(option.equals("back")) {
//			currentPage = (cp - 1) + "";
//			cp -= 1;
//		}
//		
//		List<ProductCategory> products = productCategoryService.selectProductsByPage((cp-1) * 6);	
//		
		UserInfo userProfile = null;
		if(auth!=null) {
			if(auth.isAuthenticated()) {
				User loginUser = (User) auth.getPrincipal();
				userProfile = userService.selectUserByName(loginUser.getUsername());
			}
		}
//		
		// Other data
		model.addAttribute("user", userProfile);
//		model.addAttribute("pageSize", pageableService.getTotalPages());
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("products", products);
//		model.addAttribute("productsAvailable", productCategoryService.selectProducts().size());
		
		return "store";
	}	
}
