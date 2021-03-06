package com.jyc.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyc.model.Order;
import com.jyc.service.OrderService;

/**
 * 后台订单相关管理
 * 
 * @author 12430
 *
 */
@Controller
@RequestMapping("/background/order")
public class AdminOrderController {
	/**
	 * 订单
	 */
	@Autowired
	private OrderService service;

	/**
	 * 订单显示操作
	 * 
	 * @param map        page、order、第几页
	 * @param pageNumber 第几页
	 * @param pageSize   一页几条
	 * @param o          查询实体
	 * @return 订单列表
	 */
	@RequestMapping("/order-list")
	public String orderManage(Map<String, Object> map, @RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "5") Integer pageSize, Order o) {
		PageHelper.startPage(pageNumber, pageSize);
		List<Order> order = service.findAll(o);
		PageInfo<Order> page = new PageInfo<Order>(order);
		map.put("order", order);
		map.put("page", page);
		map.put("pageNumber", pageNumber);
		return "background/order/order-list";
	}

	/**
	 * 修改订单
	 * 
	 * @param id     订单号
	 * @param userId 用户号
	 * @param status 状态
	 * @param map    提示
	 * @return 成功list，失败回显
	 */
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
			}
		}
		return "background/order/order-detail";
	}

	/**
	 * 删除管理员，实际不删，加了一个deleteFlag标记，ajax
	 * 
	 * @param ids 要删除的id
	 * @return 返回JSON数据
	 */
	@RequestMapping(value = "/order-delete", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteAdmin(@RequestParam(required = true, name = "ids") Integer ids) {
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

	/**
	 * 删除多个，实际不删，ajax
	 * 
	 * @param ids 传来的管理员数组
	 * @return 返回一个JSON
	 */
	@RequestMapping(value = "/order-delete-many", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteAdmin(@RequestParam(required = true, name = "ids[]") Integer[] ids) {
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
