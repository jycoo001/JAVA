package com.jyc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.GoodsDetail;

@Mapper
public interface GoodsDetailDAO extends BaseDAO {

	public GoodsDetail findByGoodsId(Integer id);
}
