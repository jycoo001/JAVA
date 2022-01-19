package com.jyc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.Back;

@Mapper
public interface BackDAO extends BaseDAO {

	public Back findByOrderId(String OrderId);
}
