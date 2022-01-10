package com.jyc.service.impl;

import java.util.List;

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

	@Override
	public List<Admin> findByIds(Integer[] ids) {
		return dao.findByIds(ids);
	}

	@Override
	public int updateFlagIds(Integer[] ids) {
		return dao.updateFlagByIds(ids);
	}

}
