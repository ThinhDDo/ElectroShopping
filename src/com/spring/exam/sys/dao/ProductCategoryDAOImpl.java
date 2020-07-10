package com.spring.exam.sys.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.exam.sys.model.ProductCategory;

@Repository
public class ProductCategoryDAOImpl implements ProductCategoryDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ProductCategory> selectProducts() {
		return sqlSession.selectList("ProductCategoryMapper.selectProducts");
	}

	@Override
	public List<ProductCategory> selectProductsByManufacturer(String name) {
		return sqlSession.selectList("ProductCategoryMapper.selectProductsByManufacture", name);
	}

	@Override
	public List<ProductCategory> selectProductsByPage(int page) {
		return sqlSession.selectList("ProductCategoryMapper.selectProductsByPage", page);
	}

	@Override
	public ProductCategory selectProductById(int id) {
		return sqlSession.selectOne("ProductCategoryMapper.selectProductById", id);
	}

	@Override
	public List<ProductCategory> selectProductByName(String name) {
		return sqlSession.selectList("ProductCategoryMapper.selectProductByName", name);
	}

}
