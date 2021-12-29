package com.jyc.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyc.dao.CartDAO;
import com.jyc.model.Cart;
import com.jyc.model.User;
import com.jyc.service.CartService;

@Service
public class CartServiceImpl extends BaseServiceImpl implements CartService {

	@Autowired
	private CartDAO dao;

	@Override
	public List<Cart> findByUserId(Integer id) {
		List<Cart> carts = dao.findByUserId(id);
		if (carts != null) {
			return carts;
		}
		return null;
	}

	@Override
	public List<Cart> findByGoodsId(Integer id) {
		return dao.findByGoodsId(id);
	}

	@Override
	public int handler(List<Integer> idsI, List<Integer> idsN, User user) {
		int row1 = 0;
		List<Cart> carts = findByUserId(user.getId());
		if (carts != null && carts.size() > 0) {
			for (int i = 0; i < carts.size(); i++) {
				boolean b = true;
				int j = 0;
				for (; j < idsI.size(); j++) {
					if (carts.get(i).getGoodsId() == idsI.get(j)) {
						b = false;
						break;
					}
				}
				if (!b) {
					Cart cart = carts.get(i);
					cart.setCount(idsN.get(j) + 1);
					cart.setUpdateTime(Calendar.getInstance().getTime());
					row1 += update(cart);
					idsI.remove(j);
					idsN.remove(j);
				} else {
					Cart cart = carts.get(i);
					cart.setCount(1);
					cart.setUpdateTime(Calendar.getInstance().getTime());
					row1 += update(cart);
				}
			}
			while (idsI.size() > 0) {
				Cart cart = new Cart();
				cart.setCount(idsN.get(0));
				cart.setCreatTime(Calendar.getInstance().getTime());
				cart.setGoodsId(idsI.get(0));
				cart.setUpdateTime(Calendar.getInstance().getTime());
				cart.setUserId(user.getId());
				row1 += insert(cart);
				idsI.remove(0);
				idsN.remove(0);
			}
		} else {
			while (idsI.size() > 0) {
				Cart cart = new Cart();
				cart.setCount(idsN.get(0));
				Calendar calendar = Calendar.getInstance();
				cart.setCreatTime(calendar.getTime());
				cart.setGoodsId(idsI.get(0));
				cart.setUpdateTime(calendar.getTime());
				cart.setUserId(user.getId());
				row1 += insert(cart);
				idsI.remove(0);
				idsN.remove(0);
			}
		}
		return row1;
	}

}
