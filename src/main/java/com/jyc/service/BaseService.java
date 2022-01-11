package com.jyc.service;

import java.util.Date;
import java.util.List;

import com.jyc.model.ViewDate;

public interface BaseService {
	public <T> List<T> findAll(T t);

	public <T> T findById(Integer id);

	public <T> List<T> findByParentId(Integer id);

	public <T> int insert(T t);

	public <T> int update(T t);

	public int deleteById(Integer id);

	public int deleteByIds(Integer[] ids);

	public int findCount();

	public List<ViewDate> findFromTo(String from, Date to);

}
