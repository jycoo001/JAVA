package com.jyc.service;

import java.util.Map;

import com.jyc.model.User;

public interface OrderService extends BaseService {
	public boolean handler(String cartList, User user, Map<String, Object> map);

}
