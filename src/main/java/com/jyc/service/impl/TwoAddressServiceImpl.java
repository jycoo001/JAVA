package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.TwoAddressDAO;
import com.jyc.service.TwoAddressService;

@Service
public class TwoAddressServiceImpl extends BaseServiceImpl implements TwoAddressService {

	@SuppressWarnings("unused")
	@Autowired
	private TwoAddressDAO dao;
}
