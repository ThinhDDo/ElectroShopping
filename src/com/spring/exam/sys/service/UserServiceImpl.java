package com.spring.exam.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.exam.sys.dao.UserDAO;
import com.spring.exam.sys.model.UserInfo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void insertUser(UserInfo user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.insertUser(user);
	}
	
	@Override
	public UserInfo selectUserByName(String username) {
		return userDAO.selectUserByName(username);
	}
	
	@Override
	public void updateUser(UserInfo user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDAO.updateUser(user);
	}
}
