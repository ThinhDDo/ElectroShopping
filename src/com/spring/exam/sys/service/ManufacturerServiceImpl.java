package com.spring.exam.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exam.sys.dao.ManufacturerDAO;
import com.spring.exam.sys.model.Manufacturer;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

	@Autowired
	private ManufacturerDAO manufacturerDAO;
	
	@Override
	public List<Manufacturer> selectManufacturers() {
		return manufacturerDAO.selectManufacturers();
	}

}
