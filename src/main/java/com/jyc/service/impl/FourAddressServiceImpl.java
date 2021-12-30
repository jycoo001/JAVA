package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.FourAddressDAO;
import com.jyc.service.FourAddressService;

@Service
public class FourAddressServiceImpl extends BaseServiceImpl implements FourAddressService {

	@SuppressWarnings("unused")
	@Autowired
	private FourAddressDAO dao;
}
