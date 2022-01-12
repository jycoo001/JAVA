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

	/**
	 * 购物车处理 idsI代表商品id idsN代表商品数量
	 *
	 */
	@Override
	public int handler(List<Integer> idsI, List<Integer> idsN, User user) {
		int row1 = 0;
		// 得到所有的此用户cart记录
		List<Cart> carts = findByUserId(user.getId());
		// cart不是空
		if (carts != null && carts.size() > 0) {
			// 购物车遍历，是否有商品id相同的
			for (int i = 0; i < carts.size(); i++) {
				boolean b = true;
				int j = 0;
				for (; j < idsI.size(); j++) {
					// 如果商品id相同,标记记为false，推出此循环
					if (carts.get(i).getGoodsId() == idsI.get(j)) {
						b = false;
						break;
					}
				}
				if (!b) {
					// 如果存在相同的商品Id
					Cart cart = carts.get(i);
					// 设置商品数量
					cart.setCount(idsN.get(j) + 1);
					// 设置更新时间
					cart.setUpdateTime(Calendar.getInstance().getTime());
					// 更新此条数据
					row1 += update(cart);
					// 移除id和数量的相应
					idsI.remove(j);
					idsN.remove(j);
				} else {
					// 如果没有相同的，设置为零
					Cart cart = carts.get(i);
					cart.setCount(1);
					cart.setUpdateTime(Calendar.getInstance().getTime());
					row1 += update(cart);
				}
			}
			// 如果遍历完，商品id还有，添加记录，直至没有
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
			// cart是空,添加
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
