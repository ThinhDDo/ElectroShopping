package com.spring.exam.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exam.sys.dao.CartDAO;
import com.spring.exam.sys.model.Cart;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDAO cartDAO;
	
	@Override
	public void insertNewCart(Cart cart) {
		cartDAO.insertNewCart(cart);
	}

	@Override
	public List<Cart> selectCartsByUsername(String username) {
		return cartDAO.selectCartsByUsername(username);
	}

	@Override
	public Cart selectCartById(int cart_id) {
		return cartDAO.selectCartById(cart_id);
	}

}
