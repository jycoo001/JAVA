package com.jyc.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyc.service.GoodsService;
import com.jyc.service.OrderService;
import com.jyc.service.UserService;

/**
 * 后台主页
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background")
public class BackgroundController {
	@Autowired
	private UserService usService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderService orderService;

	@RequestMapping("/index")
	public String toIndex(HttpSession session, Map<String, Object> map) {
		map.put("detail", session.getAttribute("detail"));
		session.removeAttribute("detail");
		return "background/index";
	}

	@RequestMapping("/welcome")
	public String welcome(Map<String, Object> map) {
		int count = usService.findCount();
		map.put("userCount", count);
		int count1 = goodsService.findCount();
		map.put("goodsCount", count1);
		int count2 = orderService.findCount();
		map.put("orderCount", count2);
		return "background/welcome";
	}

}
