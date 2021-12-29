package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jyc.model.Cart;

@Mapper
public interface CartDAO extends BaseDAO {

	public List<Cart> findByUserId(Integer id);

	public List<Cart> findByGoodsId(Integer id);

	public List<Cart> findByUserIdAndGoodsId(@Param(value = "ids") Integer[] ids);
}
