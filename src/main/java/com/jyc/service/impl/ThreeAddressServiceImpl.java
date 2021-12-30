package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.ThreeAddressDAO;
import com.jyc.service.ThreeAddressService;

@Service
public class ThreeAddressServiceImpl extends BaseServiceImpl implements ThreeAddressService {

	@SuppressWarnings("unused")
	@Autowired
	private ThreeAddressDAO dao;
}
