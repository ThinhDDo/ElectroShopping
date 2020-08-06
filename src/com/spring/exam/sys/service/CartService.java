package com.spring.exam.sys.service;

import java.util.List;

import com.spring.exam.sys.model.Cart;

public interface CartService {
	void insertNewCart(Cart cart);
	List<Cart> selectCartsByUsername(String username);
	Cart selectCartById(int cart_id);
}
