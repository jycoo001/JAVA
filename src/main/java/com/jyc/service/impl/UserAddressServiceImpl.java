package com.jyc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.UserAddressDAO;
import com.jyc.model.UserAddress;
import com.jyc.service.UserAddressService;

@Service
public class UserAddressServiceImpl extends BaseServiceImpl implements UserAddressService {

	@Autowired
	private UserAddressDAO dao;

	@Override
	public List<UserAddress> findByUserId(Integer id) {
		return dao.findByUserId(id);
	}

}
