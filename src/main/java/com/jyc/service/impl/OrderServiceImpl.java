package com.jyc.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.CartDAO;
import com.jyc.dao.OrderDAO;
import com.jyc.dao.OrderDetailDAO;
import com.jyc.dao.UserAddressDAO;
import com.jyc.model.Cart;
import com.jyc.model.Order;
import com.jyc.model.OrderDetail;
import com.jyc.model.User;
import com.jyc.model.UserAddress;
import com.jyc.service.OrderService;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	@Autowired
	private OrderDAO dao;
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	@Autowired
	private UserAddressDAO addressDAO;
	@Autowired
	private CartDAO cartDAO;

	public boolean handler(String cartList, User user) {
		if (user == null) {
			return false;
		}
		Order order = new Order();

		order.setUserId(user.getId());
		// user付款操作
		order.setPay("已付款");
		order.setCreatTime(Calendar.getInstance().getTime());
		order.setUpdateTime(Calendar.getInstance().getTime());
		order.setStatus("未发货");
		order.setUserStatus("未收货");

		UserAddress userAddress = new UserAddress();
		userAddress.setUserId(user.getId());
		userAddress.setDefaults("1");
		List<UserAddress> us = addressDAO.findAll(userAddress);
		order.setAddressId(us.get(0).getAddressId());

		String[] carts = cartList.split("}");

		double price = 0;
		int row2 = 0;
		for (int i = 0; i < carts.length - 1; i++) {
			String[] to = carts[i].substring(2).split(",");
			OrderDetail od = new OrderDetail();
			od.setCreatTime(Calendar.getInstance().getTime());
			od.setUpdateTime(Calendar.getInstance().getTime());
			for (int j = 0; j < to.length; j++) {
				if (to[j].contains("id")) {
					String tx = to[j].split(":")[1];
					tx = tx.substring(1, tx.length() - 1);
					Integer goodsId = Integer.valueOf(tx);
					od.setGoodsId(goodsId);
				}
				if (to[j].contains("num")) {
					String tx = to[j].split(":")[1];
					Integer count = Integer.valueOf(tx);
					od.setCount(count);
				}
				if (to[j].contains("price")) {
					od.setPrice(Double.valueOf(to[j].split(":")[1]));
				}
			}
			price += od.getCount() * od.getPrice();
			od.setUserId(user.getId());
			row2 = orderDetailDAO.insert(od);
		}

		order.setPrice(price);
		int row1 = dao.insert(order);
		int row3 = 0;
		List<Cart> list = cartDAO.findByUserId(user.getId());
		if (list != null && list.size() > 0) {
			for (Cart cart : list) {
				cart.setCount(1);
				row3 += cartDAO.update(cart);
			}
		}

		if (row1 > 0 && row2 > 0 && row3 > 0) {
			return true;
		} else {
			return false;
		}

	}

}
