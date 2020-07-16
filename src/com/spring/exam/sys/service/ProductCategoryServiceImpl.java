package com.spring.exam.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exam.sys.dao.ProductCategoryDAO;
import com.spring.exam.sys.model.Category;
import com.spring.exam.sys.model.ProductCategory;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService, PageableProductService {
	
	@Autowired
	private ProductCategoryDAO productCategoryDAO;

	@Override
	public List<ProductCategory> selectProducts() {
		return productCategoryDAO.selectProducts();
	}

	@Override
	public List<ProductCategory> selectProductsByManufacturer(String name) {
		return productCategoryDAO.selectProductsByManufacturer(name);
	}

	@Override
	public List<ProductCategory> selectProductsByPage(int page) {
		return productCategoryDAO.selectProductsByPage(page);
	}

	@Override
	public ProductCategory selectProductById(int id) {
		return productCategoryDAO.selectProductById(id);
	}

	@Override
	public List<ProductCategory> selectProductsByName(String name, int category_id) {
		return productCategoryDAO.selectProductByName(name, category_id);
	}

	@Override
	public int getTotalPages() {
		int pageSize = productCategoryDAO.selectProducts().size();
		return pageSize % 2 == 0 ? pageSize/6 : (pageSize/6) + 1 ;
	}

	@Override
	public List<Category> selectCategories() {
		return productCategoryDAO.selectCategories();
	}
	
	@Override
	public List<ProductCategory> selectProductsByCategory(String category_name) {
		return productCategoryDAO.selectProductsByCategory(category_name);
	}
}
