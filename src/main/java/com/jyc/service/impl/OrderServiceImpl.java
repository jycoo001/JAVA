package com.jyc.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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

	/**
	 * 处理
	 */
	public boolean handler(String cartList, User user) {
		// user为空，未登录
		if (user == null) {
			return false;
		}
		// 新建一个订单
		Order order = new Order();
		String uuid = UUID.randomUUID().toString();
		// 设置UUID为订单业务主键编号
		order.setOrderId(uuid);
		// 设置用户Id
		order.setUserId(user.getId());

		// 设置订单创建时间
		order.setCreatTime(Calendar.getInstance().getTime());
		// 设置订单更新时间
		order.setUpdateTime(Calendar.getInstance().getTime());
		// 设置订单未发货
		order.setStatus("未发货");
		// 设置订单未收货
		order.setUserStatus("未收货");

		// 新建用户地址实体
		UserAddress userAddress = new UserAddress();
		// 用户地址实体设置用户id
		userAddress.setUserId(user.getId());
		// 设置用户地址为默认的
		userAddress.setDefaults("1");
		// 得到用户默认的地址
		List<UserAddress> us = addressDAO.findAll(userAddress);
		// 将用户默认地址设置到订单中
		order.setAddressId(us.get(0).getAddressId());

		// 分割字符串
		String[] carts = cartList.split("}");

		// 价格
		double price = 0;
		int row2 = 0;
		for (int i = 0; i < carts.length - 1; i++) {
			// 再次分割剩余的字符串
			String[] to = carts[i].substring(2).split(",");
			// new一个订单详细表
			OrderDetail od = new OrderDetail();
			// 设置订单详细创建时间
			od.setCreatTime(Calendar.getInstance().getTime());
			// 设置订单详细更新时间
			od.setUpdateTime(Calendar.getInstance().getTime());
			for (int j = 0; j < to.length; j++) {
				// 得到商品的id，并设置
				if (to[j].contains("id")) {
					String tx = to[j].split(":")[1];
					tx = tx.substring(1, tx.length() - 1);
					Integer goodsId = Integer.valueOf(tx);
					od.setGoodsId(goodsId);
				}
				// 得到数量并设置
				if (to[j].contains("num")) {
					String tx = to[j].split(":")[1];
					Integer count = Integer.valueOf(tx);
					od.setCount(count);
				}
				// 得到单个价格并设置
				if (to[j].contains("price")) {
					od.setPrice(Double.valueOf(to[j].split(":")[1]));
				}
			}
			// 价格累加
			price += od.getCount() * od.getPrice();
			// 订单详细设置用户id
			od.setUserId(user.getId());
			// 设置订单id
			od.setOrderId(uuid);
			// 订单详细添加一条记录
			row2 = orderDetailDAO.insert(od);
		}
		// user付款操作

		order.setPrice(price);
		order.setPay("已付款");

		// MySQL中在订单表添加一条记录
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
