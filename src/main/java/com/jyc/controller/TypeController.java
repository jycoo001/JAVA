package com.jyc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.OneType;
import com.jyc.service.OneTypeService;

/**
 * 类别控制器
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background/type")
public class TypeController {
	@Autowired
	private OneTypeService service;

	@RequestMapping("/list")
	public String type(Map<String, Object> map, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, OneType one) {

		PageHelper.startPage(pageNumber, pageSize);
		List<OneType> list = service.findAll(one);
		PageInfo<OneType> page = new PageInfo<>(list);

		map.put("page", page);
		map.put("pageNumber", pageNumber);
		map.put("list", list);
		map.put("type", one);
		return "background/type/type-list";
	}

	@RequestMapping("/type-edit")
	public String typeEdit(OneType onetype, Map<String, Object> map) {
		if (onetype.getName() != null && onetype.getName().trim().length() > 0 && onetype.getHidden() != null) {
			int row = service.update(onetype);
			if (row > 0) {
				return "redirect:/background/type/list";
			} else {
				map.put("type", onetype);
			}
		} else {
			OneType one = service.findById(onetype.getId());
			map.put("type", one);
		}
		return "background/type/type-detail";
	}
}
