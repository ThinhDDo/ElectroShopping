package com.spring.exam.sys.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.exam.sys.model.UserInfo;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void insertUser(UserInfo user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
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

	@Override
	public void updatePassword(UserInfo user) {		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		sqlSession.selectOne("UserMapper.updateUserPassword", user);
	}
}
