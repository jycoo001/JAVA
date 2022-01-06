package com.jyc.service;

import java.util.List;

import com.jyc.model.UserAddress;

public interface UserAddressService extends BaseService {

	public List<UserAddress> findByUserId(Integer id);
}
