package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.jyc.dao.ThreeTypeDAO;
import com.jyc.service.ThreeTypeService;

public class ThreeTypeServiceImpl extends BaseServiceImpl implements ThreeTypeService {
	@SuppressWarnings("unused")
	@Autowired
	private ThreeTypeDAO dao;
}
