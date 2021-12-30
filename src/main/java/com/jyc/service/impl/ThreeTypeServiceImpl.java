package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.ThreeTypeDAO;
import com.jyc.service.ThreeTypeService;

@Service
public class ThreeTypeServiceImpl extends BaseServiceImpl implements ThreeTypeService {
	@SuppressWarnings("unused")
	@Autowired
	private ThreeTypeDAO dao;
}
