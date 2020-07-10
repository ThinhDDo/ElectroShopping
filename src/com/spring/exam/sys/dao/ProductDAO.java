package com.spring.exam.sys.dao;

import java.util.List;

import com.spring.exam.sys.model.Product;

public interface ProductDAO {
	List<Product> selectProducts();
	List<Product> selectProductsByManufacturer(String name);
	List<Product> selectProductsByPage(int page);
	List<Product> selectProductByName(String name);
	Product selectProductById(int id);
}
