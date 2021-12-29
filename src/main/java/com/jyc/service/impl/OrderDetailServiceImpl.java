package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.OrderDetailDAO;
import com.jyc.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl extends BaseServiceImpl implements OrderDetailService {

	@SuppressWarnings("unused")
	@Autowired
	private OrderDetailDAO dao;

}
