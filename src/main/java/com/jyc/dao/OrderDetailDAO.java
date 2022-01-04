package com.jyc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jyc.model.OrderDetail;

@Mapper
public interface OrderDetailDAO extends BaseDAO {

	public List<OrderDetail> findByOrderId(String id);
}
