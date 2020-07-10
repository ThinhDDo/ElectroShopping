package com.spring.exam.sys.dao;

import java.util.List;

import com.spring.exam.sys.model.Product;
import com.spring.exam.sys.model.ProductCategory;

public interface ProductCategoryDAO {
	List<ProductCategory> selectProducts();
	List<ProductCategory> selectProductsByManufacturer(String name);
	List<ProductCategory> selectProductsByPage(int page);
	List<ProductCategory> selectProductByName(String name);
	ProductCategory selectProductById(int id);
}
