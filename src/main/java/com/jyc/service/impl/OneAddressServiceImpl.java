package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.OneAddressDAO;
import com.jyc.service.OneAddressService;

@Service
public class OneAddressServiceImpl extends BaseServiceImpl implements OneAddressService {

	@SuppressWarnings("unused")
	@Autowired
	private OneAddressDAO dao;
}
