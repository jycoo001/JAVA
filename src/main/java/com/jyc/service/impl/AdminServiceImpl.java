package com.jyc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.AdminDAO;
import com.jyc.model.Admin;
import com.jyc.service.AdminService;

@Service
public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;

	@Override
	public Admin login(Admin admin) {
		return dao.login(admin);
	}

}
