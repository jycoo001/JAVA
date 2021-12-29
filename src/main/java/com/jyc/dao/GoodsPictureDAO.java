package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.GoodsPicture;

@Mapper
public interface GoodsPictureDAO extends BaseDAO {

	public List<GoodsPicture> findByGoodsId(Integer id);
}
