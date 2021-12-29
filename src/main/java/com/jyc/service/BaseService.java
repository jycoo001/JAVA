package com.jyc.service;

import java.util.List;

public interface BaseService {
	public <T> List<T> findAll(T t);

	public <T> T findById(Integer id);

	public <T> int insert(T t);

	public <T> int update(T t);

	public int deleteById(Integer id);

	public int deleteByIds(Integer[] ids);

}
