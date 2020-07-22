package com.spring.exam.sys.service;

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

}
