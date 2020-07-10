package com.spring.exam.sys.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.exam.sys.model.UserInfo;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertUser(UserInfo user) {
		
//		String password = passwordEncoder.encode(user.getPassword());
//		user.setPassword(password);
		sqlSession.selectOne("UserMapper.insertUser", user);
	}
	
	@Override
	public UserInfo selectUserByName(String username) {
		return sqlSession.selectOne("UserMapper.selectUserByName", username);
	}
	
	@Override
	public void updateUser(UserInfo user) {
		sqlSession.selectOne("UserMapper.updateUser", user);
	}
}
