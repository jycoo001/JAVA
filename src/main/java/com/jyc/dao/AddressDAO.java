package com.jyc.dao;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.Address;

@Mapper
public interface AddressDAO extends BaseDAO {

	public Address findByParentId(Integer id);
}
