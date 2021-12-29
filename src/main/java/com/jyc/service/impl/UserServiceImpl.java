package com.jyc.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.UserDAO;
import com.jyc.model.User;
import com.jyc.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

	@Override
	public User login(User user) {
		return dao.login(user);
	}

	@Override
	public int register(User user) {
		Calendar calendar = Calendar.getInstance();
		Date creatTime = calendar.getTime();
		user.setCreatTime(creatTime);
		String caId = user.getCardId().substring(user.getCardId().length() - 2, user.getCardId().length() - 1);
		int id = Integer.valueOf(caId);
		if (id % 2 == 0) {
			user.setSex("女");
		} else {
			user.setSex("男");
		}
		user.setUserMoney(0.0);
		user.setUserStatus("1");
		return dao.register(user);
	}
}
