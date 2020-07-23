package com.spring.exam.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.exam.sys.model.Cart;

@Repository
public class CartDAOImpl implements CartDAO {
	
	@Autowired 
	private SqlSession sqlSession;
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public void insertNewCart(Cart cart) {
		Map<String, Object> params = new HashMap<>();
		params.put("address", cart.getShipment().getAddress());
		params.put("notes", cart.getShipment().getAddress());
		params.put("username", cart.getUsername());
		params.put("cart_date", cart.getCart_date());
		params.put("country", cart.getCountry());
		params.put("city", cart.getCity());
		params.put("zipcode", cart.getZipcode());
		params.put("products", cart.getProducts());
		
		sqlSession.selectList("CartMapper.insertNewCart", params);
		
		System.out.println("cart_id generated: " + params.get("cid"));
		
		sqlSession.selectList("CartMapper.insertProductsToCart", params);
	}

	@Override
	public List<Cart> selectCartsByUsername(String username) {
		return sqlSession.selectList("CartMapper.selectCartsByUsername", username);
	}
}
