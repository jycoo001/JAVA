package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.TwoType;

@Mapper
public interface TwoTypeDAO extends BaseDAO {

	public List<TwoType> findByParentId(Integer id);
}
