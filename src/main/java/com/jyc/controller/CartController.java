package com.jyc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyc.model.User;
import com.jyc.service.CartService;

/**
 * 购物车控制器
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	/**
	 * 购物车
	 */
	@Autowired
	private CartService service;

	/**
	 * 进入购物车时，更新购物车表
	 * 
	 * @param ids
	 * @param session
	 * @param map     提示
	 * @return 购物车页面
	 */
	@RequestMapping(value = { "/index/{ids}", "/index" })
	public String cart(@PathVariable(required = false) String ids, HttpSession session, Map<String, Object> map) {
		if (ids != null && ids.trim().length() > 0) {
			String[] ids1 = ids.split(",");
			List<Integer> idsI = new ArrayList<>();
			List<Integer> idsN = new ArrayList<>();
			for (int i = 0; i < ids1.length; i++) {
				String[] idsa = ids1[i].split(":");
				idsI.add(Integer.valueOf(idsa[0]));
				idsN.add(Integer.valueOf(idsa[1]));
			}
			User user = (User) session.getAttribute("####user_login####");
			if (user != null) {
				int row = service.handler(idsI, idsN, user);
				System.out.println("修改了" + row + "行");
				return "forward/cart";
			} else {
				return "redirect:/forward/user/login";
			}
		} else {
			User user = (User) session.getAttribute("####user_login####");
			if (user != null) {
				int row = service.handler(new ArrayList<Integer>(), new ArrayList<Integer>(), user);
				System.out.println("修改了" + row + "行");
				return "forward/cart";
			} else {
				return "redirect:/forward/user/login";
			}
		}
	}

}
