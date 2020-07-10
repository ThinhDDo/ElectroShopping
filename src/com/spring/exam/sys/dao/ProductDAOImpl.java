package com.spring.exam.sys.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.exam.sys.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Product> selectProducts() {
		return sqlSession.selectList("ProductMapper.selectProducts");
	}

	@Override
	public List<Product> selectProductsByManufacturer(String name) {
		return sqlSession.selectList("ProductMapper.selectProductsByManufacture", name);
	}

	@Override
	public List<Product> selectProductsByPage(int page) {
		return sqlSession.selectList("ProductMapper.selectProductsByPage", page);
	}

	@Override
	public Product selectProductById(int id) {
		return sqlSession.selectOne("ProductMapper.selectProductById", id);
	}

	@Override
	public List<Product> selectProductByName(String name) {
		return sqlSession.selectList("ProductMapper.selectProductByName", name);
	}

}
