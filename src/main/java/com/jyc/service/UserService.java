package com.jyc.service;

import com.jyc.model.User;

public interface UserService extends BaseService {
	public User login(User user);

	public int register(User user);

	public int handlerAddress(Integer ud);
}
