package com.jyc.service;

import com.jyc.model.GoodsDetail;

public interface GoodsDetailService extends BaseService {

	public GoodsDetail findByGoodsId(Integer id);
}
