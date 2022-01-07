package com.jyc.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyc.model.Goods;
import com.jyc.model.Logo;
import com.jyc.model.OneAddress;
import com.jyc.model.OneType;
import com.jyc.model.Shuffling;
import com.jyc.model.ThreeAddress;
import com.jyc.model.TwoAddress;
import com.jyc.model.User;
import com.jyc.service.GoodsService;
import com.jyc.service.LogoService;
import com.jyc.service.OneAddressService;
import com.jyc.service.OneTypeService;
import com.jyc.service.ShufflingService;
import com.jyc.service.ThreeAddressService;
import com.jyc.service.TwoAddressService;

@Controller
@RequestMapping("")
public class IndexController {
	@Autowired
	private ShufflingService service;
	@Autowired
	private OneTypeService oneService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private LogoService logoService;
	@Autowired
	private OneAddressService one;
	@Autowired
	private TwoAddressService two;
	@Autowired
	private ThreeAddressService three;

	@RequestMapping("")
	public String index(HttpSession session, Map<String, Object> map) {

		Shuffling shuffling = new Shuffling();
		shuffling.setStatus("1");

		List<Shuffling> shufflings = service.findAll(shuffling);
		map.put("shufflings", shufflings);

		OneType oneType = new OneType();
		oneType.setHidden("1");
		List<OneType> type = oneService.findAll(oneType);
		map.put("type", type);
		Goods good = new Goods();
		good.setHot(1000);
		List<Goods> goods = goodsService.findAll(good);
		map.put("goods", goods);
		User user = (User) session.getAttribute("####user_login####");
		map.put("detail", session.getAttribute("detail"));
		Logo logo = new Logo();
		logo.setStatus("1");
		List<Logo> list = logoService.findAll(logo);
		map.put("logo", list.get(0));
		session.removeAttribute("detail");
		if (user != null) {
			map.put("user", user);
		}

		// 地址
		List<OneAddress> onex = one.findAll(null);
		List<TwoAddress> twox = two.findAll(null);
		List<ThreeAddress> threex = three.findAll(null);
		map.put("one", onex);
		map.put("two", twox);
		map.put("three", threex);

		return "forward/index";
	}
}
