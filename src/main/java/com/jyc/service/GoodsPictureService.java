package com.jyc.service;

import java.util.List;

import com.jyc.model.GoodsPicture;

public interface GoodsPictureService extends BaseService {

	public List<GoodsPicture> findByGoodsId(Integer id);
}
