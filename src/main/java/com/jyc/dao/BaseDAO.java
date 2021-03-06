package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyc.model.ViewDate;

public interface BaseDAO {

	public <T> List<T> findAll(T t);

	public <T> T findById(Integer id);

	public int findCount();

	public <T> int insert(T t);

	public <T> int update(T t);

	public int deleteById(Integer id);

	public int deleteByIds(@Param(value = "ids") Integer[] ids);

	public <T> List<T> findByParentId(Integer id);

	public List<ViewDate> findFromTo(@Param(value = "fromTo") String[] fromTo);
}
