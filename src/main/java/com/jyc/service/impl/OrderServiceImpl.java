package com.jyc.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.BackDAO;
import com.jyc.dao.CartDAO;
import com.jyc.dao.OrderDAO;
import com.jyc.dao.OrderDetailDAO;
import com.jyc.dao.UserAddressDAO;
import com.jyc.dao.UserDAO;
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
	private BackDAO back;
	@Autowired
	private UserAddressDAO addressDAO;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private UserDAO userDAO;

	/**
	 * 处理
	 */
	public boolean handler(String cartList, User user, Map<String, Object> map) {
		// user为空，未登录
		if (user == null) {
			map.put("detail", "您尚未登陆");
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
		Double money = user.getUserMoney() - price;
		if (money > 0) {
			user.setUserMoney(money);
			order.setPay("已付款");
			int row4 = userDAO.update(user);
			if (row4 > 0) {
				map.put("detail", "付款成功");
			} else {
				map.put("detail", "付款失败");
			}
		} else {
			map.put("detail", "您账户中金额不足！请充值");
			return false;
		}

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
			map.put("detail", "生成订单失败");
			return false;
		}

	}

	@Override
	public int deleteById(Integer id) {
		Order order = dao.findById(id);
		List<OrderDetail> list = order.getDetails();
		List<Integer> listI = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			listI.add(list.get(i).getId());
		}
		int row = orderDetailDAO.deleteByIds((Integer[]) listI.toArray());
		row += dao.deleteById(id);
		row += back.deleteById(order.getBack().getId());
		return row;
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		List<Integer> listI = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			Order order = dao.findById(ids[i]);
			List<OrderDetail> de = order.getDetails();
			for (int j = 0; j < de.size(); j++) {
				listI.add(de.get(j).getId());
			}
			listB.add(order.getBack().getId());
		}
		int row = orderDetailDAO.deleteByIds((Integer[]) listI.toArray());
		row += dao.deleteByIds(ids);
		row += back.deleteByIds((Integer[]) listB.toArray());
		return row;
	}

}
