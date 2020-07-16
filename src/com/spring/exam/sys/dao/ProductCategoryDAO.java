package com.spring.exam.sys.dao;

import java.util.List;

import com.spring.exam.sys.model.Category;
import com.spring.exam.sys.model.ProductCategory;

public interface ProductCategoryDAO {
	List<ProductCategory> selectProducts();
	List<ProductCategory> selectProductsByManufacturer(String name);
	List<ProductCategory> selectProductsByPage(int page);	
	ProductCategory selectProductById(int id);
	List<ProductCategory> selectProductsByCategory(String category_name);
	List<Category> selectCategories();	
	
	// Search function
	List<ProductCategory> selectProductByName(String name, int category_id);
}
