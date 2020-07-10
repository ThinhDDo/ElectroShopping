package com.spring.exam.sys.service;

import com.spring.exam.sys.model.UserInfo;

public interface UserService {
	void insertUser(UserInfo user);
	UserInfo selectUserByName(String username);
	void updateUser(UserInfo user);
}
