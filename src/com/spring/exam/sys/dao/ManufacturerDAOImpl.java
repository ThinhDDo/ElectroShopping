package com.spring.exam.sys.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.exam.sys.model.Manufacturer;

@Repository
public class ManufacturerDAOImpl implements ManufacturerDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Manufacturer> selectManufacturers() {
		return sqlSession.selectList("ManufacturerMapper.selectManufacturers");
	}

}
