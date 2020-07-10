package com.spring.exam.sys.dao;

import com.spring.exam.sys.model.UserInfo;

public interface UserDAO {
	void insertUser(UserInfo user);
	UserInfo selectUserByName(String username);
	void updateUser(UserInfo user);
}
