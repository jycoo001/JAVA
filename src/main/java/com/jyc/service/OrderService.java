package com.jyc.service;

import com.jyc.model.User;

public interface OrderService extends BaseService {
	public boolean handler(String cartList, User user);

}
