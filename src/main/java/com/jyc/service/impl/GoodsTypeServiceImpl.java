package com.jyc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.GoodsTypeDAO;
import com.jyc.model.GoodsType;
import com.jyc.service.GoodsTypeService;

@Service
public class GoodsTypeServiceImpl extends BaseServiceImpl implements GoodsTypeService {

	@Autowired
	private GoodsTypeDAO dao;

	@Override
	public int deleteById(Integer id) {
		GoodsType goodsType = dao.findById(id);
		int row = 0;
		if (goodsType.getChildrens() != null && goodsType.getChildrens().size() > 0) {
			for (GoodsType goodsType2 : goodsType.getChildrens()) {
				row += deleteById(goodsType2.getId());
			}
			row += dao.deleteById(id);
		} else {
			row += dao.deleteById(id);
		}
		return row;
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			getIds(ids[i], list);
		}
		return dao.deleteByIds(list.toArray(ids));
	}

	public void getIds(Integer id, List<Integer> list) {
		GoodsType goodsType = dao.findById(id);
		if (goodsType.getChildrens() != null && goodsType.getChildrens().size() > 0) {
			for (GoodsType goodsType2 : goodsType.getChildrens()) {
				getIds(goodsType2.getId(), list);
			}
			list.add(goodsType.getId());
		} else {
			list.add(goodsType.getId());
		}
	}

}
