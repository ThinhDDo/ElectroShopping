package com.spring.exam.sys.service;

import java.util.List;

import com.spring.exam.sys.model.Product;

public interface ProductService {
	List<Product> selectProducts();
	List<Product> selectProductsByManufacturer(String name);
	List<Product> selectProductsByPage(int page);
	List<Product> selectProductsByName(String name);
	Product selectProductById(int id);
}
