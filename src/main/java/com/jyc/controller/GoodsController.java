package com.jyc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.Goods;
import com.jyc.model.Shuffling;
import com.jyc.service.GoodsPictureService;
import com.jyc.service.GoodsService;

@Controller
@RequestMapping("/background/goods")
public class GoodsController {
	@Autowired
	private GoodsService service;
	@Autowired
	private GoodsPictureService goodsPictureService;

	@RequestMapping(value = { "", "/" })
	public String goods(Shuffling shuffling, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, Map<String, Object> map) {
		PageHelper.startPage(pageNumber, pageSize);
		List<Shuffling> list = service.findAll(shuffling);
		PageInfo<Shuffling> page = new PageInfo<>(list);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		map.put("goods", shuffling);
		map.put("list", list);
		return "background/goods/goods-list";
	}

	@GetMapping("/goods-add")
	public String addGoodsGet() {
		return "background/goods/goods-add";
	}

	@PostMapping("/goods-add")
	public String addGoods(Goods goods, Map<String, Object> map) {
		if (goods.equals("")) {
			int row1 = service.insert(goods);
			if (row1 > 0) {
				map.put("detail", "添加成功");
				return "background/goods/goods-list";
			} else {
				map.put("goods", goods);
				map.put("detail", "添加失败");
			}
		} else {
			map.put("detail", "输入有空");
			map.put("goods", goods);
		}
		return "background/goods/goods-add";
	}

	@RequestMapping("/goods-detail")
	public String upda(Goods goods, Map<String, Object> map, HttpSession session) {
		if (goods.equals("") && goods.getId() != null) {
			int row = service.update(goods);
			if (row > 0) {
				session.setAttribute("detail", "修改成功");
				return "redirect:/background/goods";
			} else {
				map.put("goods", goods);
				map.put("detail", "修改失败");
				return "background/goods/goods-detail";
			}
		} else {
			if (goods.getId() != null) {
				Goods goods1 = service.findById(goods.getId());
				map.put("goods", goods1);
			}
		}
		return "background/goods/goods-detail";
	}

	@RequestMapping(value = "/goods-delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(required = true, name = "ids") Integer ids) {
		Map<String, Object> map = new HashMap<>();
		int row = service.deleteById(ids);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}

	@RequestMapping(value = "/goods-delete-many", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> delete(@RequestParam(required = true, name = "ids[]") Integer[] ids) {
		Map<String, Object> map = new HashMap<>();
		int row = service.deleteByIds(ids);
		if (row > 0) {
			map.put("detail", "删除成功");
			map.put("rows", row);
		} else {
			map.put("detail", "删除失败");
		}
		return map;
	}
}
