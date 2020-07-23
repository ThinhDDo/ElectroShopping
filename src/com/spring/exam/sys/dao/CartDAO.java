package com.spring.exam.sys.dao;

import java.util.List;
import java.util.Map;

import com.spring.exam.sys.model.Cart;

public interface CartDAO {
	void insertNewCart(Cart cart);
	List<Cart> selectCartsByUsername(String username);
}
