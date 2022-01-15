package com.jyc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.GoodsType;

@Mapper
public interface GoodsTypeDAO extends BaseDAO {

	public GoodsType findByTypeId(Integer id);
}
