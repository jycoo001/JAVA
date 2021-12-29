package com.jyc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyc.model.Goods;
import com.jyc.model.OneType;
import com.jyc.model.Shuffling;
import com.jyc.model.User;
import com.jyc.service.GoodsService;
import com.jyc.service.OneTypeService;
import com.jyc.service.ShufflingService;

@Controller
@RequestMapping("")
public class IndexController {
	@Autowired
	private ShufflingService service;
	@Autowired
	private OneTypeService oneService;
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("")
	public String index(HttpSession session, Map<String, Object> map) {

		List<Shuffling> shufflings = service.findAll(null);
		map.put("shufflings", shufflings);
		List<OneType> type = oneService.findAll(null);
		map.put("type", type);
		List<Goods> goods = goodsService.findAll(null);
		map.put("goods", goods);
		User user = (User) session.getAttribute("####user_login####");
		if (user != null) {
			map.put("user", user);
		}
		return "forward/index";
	}
}
