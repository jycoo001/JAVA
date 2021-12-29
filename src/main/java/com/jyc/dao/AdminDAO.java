package com.jyc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.Admin;

@Mapper
public interface AdminDAO extends BaseDAO {
	public Admin login(Admin admin);
}
