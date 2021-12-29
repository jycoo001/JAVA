package com.jyc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.User;

@Mapper
public interface UserDAO extends BaseDAO {
	public User login(User user);

	public int register(User user);

}
