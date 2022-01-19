package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.Goods;

@Mapper
public interface GoodsDAO extends BaseDAO {

	public List<Goods> findLike(String name);
}
