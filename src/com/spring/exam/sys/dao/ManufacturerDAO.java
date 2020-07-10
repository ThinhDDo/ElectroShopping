package com.spring.exam.sys.dao;

import java.util.List;

import com.spring.exam.sys.model.Manufacturer;

public interface ManufacturerDAO {
	List<Manufacturer> selectManufacturers();
}
