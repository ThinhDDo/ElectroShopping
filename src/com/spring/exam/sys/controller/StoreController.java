package com.spring.exam.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.ProductCategoryService;
import com.spring.exam.sys.service.UserService;

@Controller
public class StoreController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping(value = "/store")
	public String openStore(HttpServletRequest request) {
		request.getSession().setAttribute("pageHolder", null);
		return "redirect:/store/page/1";
	}
	
	/**
	 * Khi nguoi dung mo trang store, trang web tu dong load trang dau tien
	 * @param currentPage
	 * @param option
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(value="/store/page/{page}")
	public String openStorePage(HttpServletRequest request,
								@PathVariable("page") int page,
								Model model,
								Authentication auth) {
		PagedListHolder<?> pages;
		List<ProductCategory> products = productCategoryService.selectProducts();
		
		if(page==1) {
			pages = new PagedListHolder<ProductCategory>(products);
			pages.setPageSize(6);
			request.getSession().setAttribute("pageHolder", pages);
		} else {
			pages = (PagedListHolder<?>) request.getSession().getAttribute("pageHolder");
			int goToPage = page - 1;
			if(goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - products.size());
		int end = Math.min(begin + 5, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		String baseUrl = "/store/page/";
		
		UserInfo userProfile = null;
		if(auth!=null) {
			if(auth.isAuthenticated()) {
				User loginUser = (User) auth.getPrincipal();
				userProfile = userService.selectUserByName(loginUser.getUsername());
			}
		}
		
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("products", pages);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("user", userProfile);
		return "store";
	}	
}
