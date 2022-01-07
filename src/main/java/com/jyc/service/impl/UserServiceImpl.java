package com.jyc.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.UserAddressDAO;
import com.jyc.dao.UserDAO;
import com.jyc.model.User;
import com.jyc.model.UserAddress;
import com.jyc.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	@Autowired
	private UserAddressDAO userAddressDAO;

	@Override
	public User login(User user) {
		return dao.login(user);
	}

	@Override
	public int insert(User user) {
		user.setCreatTime(Calendar.getInstance().getTime());
		user.setLastTime(Calendar.getInstance().getTime());
		return super.insert(user);
	}

	@Override
	public int register(User user) {
		user.setCreatTime(Calendar.getInstance().getTime());
		user.setUserMoney(0.0);
		user.setUserStatus("1");
		user.setLastTime(Calendar.getInstance().getTime());
		return dao.register(user);
	}

	@Override
	public int handlerAddress(Integer id) {

		UserAddress ud = userAddressDAO.findById(id);
		int row = 0;
		List<UserAddress> user = userAddressDAO.findByUserId(ud.getUserId());
		for (int i = 0; i < user.size(); i++) {
			UserAddress user1 = user.get(i);
			user1.setDefaults("0");
			row += userAddressDAO.update(user1);
		}
		ud.setDefaults("1");
		row += userAddressDAO.update(ud);

		return row;
	}
}
