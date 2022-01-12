package com.jyc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.GoodsDetail;
import com.jyc.service.GoodsDetailService;

/**
 * 前台商品详细页
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background/goods-detail")
public class GoodsDetailController {

	@Autowired
	private GoodsDetailService service;

	@RequestMapping(value = { "", "/" })
	public String find(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, GoodsDetail goodsDetail, Map<String, Object> map) {
		PageHelper.startPage(pageNumber, pageSize);
		List<GoodsDetail> list = service.findAll(goodsDetail);
		PageInfo<GoodsDetail> page = new PageInfo<>(list);
		map.put("goodsDetail", goodsDetail);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		return "background/goods-detail/goods-detail-list";
	}

}
