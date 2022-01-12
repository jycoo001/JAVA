package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.GoodsDetailDAO;
import com.jyc.model.GoodsDetail;
import com.jyc.service.GoodsDetailService;

@Service
public class GoodsDeatilServiceImpl extends BaseServiceImpl implements GoodsDetailService {

	@SuppressWarnings("unused")
	@Autowired
	private GoodsDetailDAO dao;

	@Override
	public GoodsDetail findByGoodsId(Integer id) {
		return dao.findByGoodsId(id);
	}
}
