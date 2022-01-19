package com.jyc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.GoodsDAO;
import com.jyc.model.Goods;
import com.jyc.service.GoodsService;

@Service
public class GoodsServiceImpl extends BaseServiceImpl implements GoodsService {

	@SuppressWarnings("unused")
	@Autowired
	private GoodsDAO dao;

	@Override
	public List<Goods> findLike(String name) {
		return dao.findLike(name);
	}
}
