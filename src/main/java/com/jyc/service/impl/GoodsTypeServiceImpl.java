package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.GoodsTypeDAO;
import com.jyc.service.GoodsTypeService;

@Service
public class GoodsTypeServiceImpl extends BaseServiceImpl implements GoodsTypeService {

	@SuppressWarnings("unused")
	@Autowired
	private GoodsTypeDAO dao;
}
