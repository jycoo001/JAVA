package com.jyc.service;

import java.util.List;

import com.jyc.model.Admin;

public interface AdminService extends BaseService {

	public Admin login(Admin admin);

	public List<Admin> findByIds(Integer[] ids);

	public int updateFlagIds(Integer[] ids);

}
