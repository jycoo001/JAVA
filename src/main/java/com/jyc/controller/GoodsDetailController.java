package com.jyc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jyc.model.Goods;
import com.jyc.service.GoodsService;

/**
 * 前台商品详细页
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/goods")
public class GoodsDetailController {

	@Autowired
	private GoodsService service;

	@RequestMapping(value = { "detail", "/detail" })
	public String find(Integer id, Map<String, Object> map) {
		Goods goods = service.findById(id);
		map.put("goods", goods);
		return "forward/detail";
	}

}
