package com.jyc.service;

import java.util.List;

import com.jyc.model.Goods;

public interface GoodsService extends BaseService {

	public List<Goods> findLike(String name);
}
