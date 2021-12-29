package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.ThreeType;

@Mapper
public interface ThreeTypeDAO extends BaseDAO {

	public List<ThreeType> findByParentId(Integer id);
}
