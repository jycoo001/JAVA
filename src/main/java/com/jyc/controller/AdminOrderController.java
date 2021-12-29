package com.jyc.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.Order;
import com.jyc.service.OrderService;

@Controller
@RequestMapping("/background/order")
public class AdminOrderController {
	@Autowired
	private OrderService service;

	@RequestMapping("/order-list")
	public String orderManage(Map<String, Object> map, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize) {
		List<Order> order = service.findAll(null);
		PageHelper.startPage(pageNumber, pageSize);
		PageInfo<Order> page = new PageInfo<Order>(order);
		map.put("order", order);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		return "background/order-list";
	}

	@RequestMapping("/order-detail")
	public String detail(Integer id, String userId, String status, Map<String, Object> map) {
		Order order = service.findById(id);
		map.put("order", order);
		if (status != null && status.trim().length() > 0 && userId != null && id != null) {
			order.setStatus(status);
			order.setUpdateTime(Calendar.getInstance().getTime());
			int row = service.update(order);
			if (row > 0) {
				return "redirect:/background/order/order-list";
			} else {
				map.put("detail", "修改失败！");
				return "background/order-detail";
			}
		}
		return "background/order-detail";
	}

}
