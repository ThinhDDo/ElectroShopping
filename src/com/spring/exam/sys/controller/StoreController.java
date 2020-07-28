package com.spring.exam.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.spring.exam.sys.model.Category;
import com.spring.exam.sys.model.ProductCategory;
import com.spring.exam.sys.service.ProductCategoryService;

@Controller
@SessionAttributes(names = {"user", "categories", "qtyHeader", "companyInfo", "mycart"})
public class StoreController {
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping(value = "/store/{cate}")
	public String openStore(@PathVariable("cate") String category,
							HttpServletRequest request, // initiate the pagination
							Model model, // add variable to URI
							RedirectAttributes ra) { // add ra to remove append url
		model.addAttribute("cate", category);
		request.getSession().setAttribute("pageHolder", null);
		return "redirect:/store/{cate}/page/1";
	}
	
	/**
	 * @param currentPage
	 * @param option
	 * @param model
	 * @param auth
	 * @return
	 */
	@GetMapping(value="/store/{cate}/page/{page}")
	public String openStorePage(
								HttpServletRequest request,
								@PathVariable("page") int page,
								@PathVariable("cate") String category_name,
								Model model) {
		PagedListHolder<?> pages;
		List<ProductCategory> products = productCategoryService.selectProductsByCategory(category_name);
		
		if(page==1) {
			pages = new PagedListHolder<ProductCategory>(products);
			pages.setPageSize(6);
			request.getSession().setAttribute("pageHolder", pages);
		} else {
			pages = (PagedListHolder<?>) request.getSession().getAttribute("pageHolder");
			int goToPage = page - 1; // Page list start with 0
			if(goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
			
		int begin = -1;
		int end = -1;
		int current = pages.getPage() + 1;
		if(current == 1) { // 1st page
			begin = Math.max(1, current - pages.getPageCount());
			end = Math.min(begin + 1,  pages.getPageCount());
		} else if(current > 1 && current < pages.getPageCount()) { // middle pages
			begin = Math.max(1, current - 1);
			end = Math.min(begin + 2,  pages.getPageCount());
		} else { // last page
			begin = Math.max(1, current - 1);
			end = Math.min(begin + 2, pages.getPageCount());
		}

		int totalPageCount = pages.getPageCount();
		String baseUrl = "/store/"+ category_name +"/page/";

		
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("products", pages);
		model.addAttribute("baseUrl", baseUrl);
		//model.addAttribute("user", userProfile);
		model.addAttribute("category", new Category());
		
		return "store";
	}	
}
