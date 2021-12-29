package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.UserAddress;

@Mapper
public interface UserAddressDAO extends BaseDAO {

	public List<UserAddress> findByUserId(Integer id);
}
