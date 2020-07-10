package com.spring.exam.sys.service;

import java.util.List;

import com.spring.exam.sys.model.ProductCategory;

public interface ProductCategoryService {
	List<ProductCategory> selectProducts();
	List<ProductCategory> selectProductsByManufacturer(String name);
	List<ProductCategory> selectProductsByPage(int page);
	List<ProductCategory> selectProductsByName(String name);
	ProductCategory selectProductById(int id);
}
