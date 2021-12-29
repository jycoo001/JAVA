package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.GoodsDAO;
import com.jyc.service.GoodsService;

@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

	@SuppressWarnings("unused")
	@Autowired
	private GoodsDAO dao;
}
