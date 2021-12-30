package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.UserAddressDAO;
import com.jyc.service.UserAddressService;

@Service
public class UserAddressServiceImpl extends BaseServiceImpl implements UserAddressService {

	@SuppressWarnings("unused")
	@Autowired
	private UserAddressDAO dao;

}
