package com.jyc.service;

import java.util.List;

import com.jyc.model.Cart;
import com.jyc.model.User;

public interface CartService extends BaseService {
	public List<Cart> findByUserId(Integer id);

	public List<Cart> findByGoodsId(Integer id);

	public int handler(List<Integer> idsI, List<Integer> idsN, User user);

}
