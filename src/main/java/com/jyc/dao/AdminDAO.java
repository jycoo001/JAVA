package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jyc.model.Admin;

@Mapper
public interface AdminDAO extends BaseDAO {
	public Admin login(Admin admin);

	public List<Admin> findByIds(@Param(value = "ids") Integer[] ids);

	public int updateFlagByIds(@Param(value = "ids") Integer[] ids);
}
