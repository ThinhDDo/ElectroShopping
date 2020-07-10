package com.spring.exam.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exam.sys.dao.ProductDAO;
import com.spring.exam.sys.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<Product> selectProducts() {
		return productDAO.selectProducts();
	}

	@Override
	public List<Product> selectProductsByManufacturer(String name) {
		return productDAO.selectProductsByManufacturer(name);
	}

	@Override
	public List<Product> selectProductsByPage(int page) {
		return productDAO.selectProductsByPage(page);
	}

	@Override
	public Product selectProductById(int id) {
		return productDAO.selectProductById(id);
	}

	@Override
	public List<Product> selectProductsByName(String name) {
		return productDAO.selectProductByName(name);
	}
}
